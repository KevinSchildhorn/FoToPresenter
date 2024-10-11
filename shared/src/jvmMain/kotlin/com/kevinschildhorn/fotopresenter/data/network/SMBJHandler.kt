package com.kevinschildhorn.fotopresenter.data.network

import co.touchlab.kermit.Logger
import com.hierynomus.msdtyp.AccessMask
import com.hierynomus.msfscc.FileAttributes
import com.hierynomus.mssmb2.SMB2CreateDisposition
import com.hierynomus.mssmb2.SMB2CreateOptions
import com.hierynomus.mssmb2.SMB2ShareAccess
import com.hierynomus.smbj.SMBClient
import com.hierynomus.smbj.auth.AuthenticationContext
import com.hierynomus.smbj.connection.Connection
import com.hierynomus.smbj.session.Session
import com.hierynomus.smbj.share.DiskShare
import com.hierynomus.smbj.share.File
import com.kevinschildhorn.fotopresenter.data.LoginCredentials
import com.kevinschildhorn.fotopresenter.extension.addPath
import com.kevinschildhorn.fotopresenter.ui.shared.SharedImage
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.OutputStream
import java.util.*
import kotlin.math.log


object SMBJHandler : NetworkHandler {
    private val client = SMBClient()
    private var connection: Connection? = null
    private var session: Session? = null
    private var share: DiskShare? = null
    private const val metaDataName: String = "FotoMetaData.json"
    private val logger = Logger.withTag("SMBJHandler")

    private val accessMask: Set<AccessMask> =
        setOf(
            AccessMask.FILE_READ_DATA,
            AccessMask.FILE_LIST_DIRECTORY,
            AccessMask.FILE_TRAVERSE,
            AccessMask.FILE_READ_ATTRIBUTES,
            AccessMask.GENERIC_READ,
        )
    private val attributes: Set<FileAttributes> = setOf(FileAttributes.FILE_ATTRIBUTE_NORMAL)
    private val shareAccesses: Set<SMB2ShareAccess> = setOf(SMB2ShareAccess.FILE_SHARE_READ)
    private val createDisposition: SMB2CreateDisposition = SMB2CreateDisposition.FILE_OPEN
    private val createOptions: Set<SMB2CreateOptions> =
        setOf(SMB2CreateOptions.FILE_NON_DIRECTORY_FILE)

    override val isConnected: Boolean
        get() = share != null

    override suspend fun connect(credentials: LoginCredentials): Boolean {
        try {
            logger.i { "Connecting" }
            with(credentials) {
                connection = client.connect(hostname)
                val context = AuthenticationContext(username, password.toCharArray(), null)
                val session: Session? = connection?.authenticate(context)
                share = session?.connectShare(credentials.sharedFolder) as? DiskShare
                if (share == null) {
                    logger.e { "Failed To Connect, shared was null" }
                    disconnect()
                    return false
                }
            }
        } catch (e: Exception) {
            logger.e(e) { "Failed To Connect" }
            disconnect()
            return false
        }
        return true
    }

    override suspend fun getDirectoryDetails(path: String): NetworkDirectoryDetails? {
        logger.i { "Getting Directory Details for $path" }
        share?.getFileInformation(path)?.let {
            return DefaultNetworkDirectoryDetails(
                id = it.internalInformation.indexNumber.toInt(),
                fullPath = path,
            )
        } ?: run {
            return null
        }
    }

    override suspend fun getDirectoryContents(path: String): List<NetworkDirectoryDetails> {
        logger.i { "Getting Directory Contents for $path" }
        return share?.list(path)?.map {
            SMBJNetworkDirectoryDetails(
                it,
                fullPath = path.addPath(it.fileName),
            )
        } ?: emptyList()
    }

    override suspend fun openDirectory(path: String): String? {
        logger.i { "Opening Directory $path" }
        val result = share?.openDirectory(
            path,
            accessMask,
            attributes,
            shareAccesses,
            createDisposition,
            createOptions,
        )
        return result?.path
    }

    override suspend fun openImage(path: String): SharedImage? =
        getFile(path)?.let {
            val sharedImage = SharedImage(it)
            it.close()
            sharedImage
        } ?: run { null }

    override suspend fun folderExists(path: String): Boolean? {
        return share?.folderExists(path)
    }

    override suspend fun disconnect() {
        share?.close()
        session?.close()
        connection?.close(true)
        share = null
        session = null
        connection = null
    }

    override suspend fun savePlaylist(playlistName: String, json: String): Boolean =
        writeFile(fileName = "$playlistName.json", contents = json)

    override suspend fun getPlaylists(): List<String> =
        getDirectoryContents("")
            .filter { it.fileExtension == "json" }
            .filter { !it.fileName.contains(metaDataName) }
            .mapNotNull { getFile(it.fullPath) }
            .map {
                val str = it.inputStream.readAllBytes().decodeToString()
                it.close()
                str
            }

    override suspend fun setMetadata(json: String): Boolean {
        logger.i { "Setting Metadata" }
        return writeFile(fileName = metaDataName, contents = json)
    }

    override suspend fun getMetadata(): String? = getFile(metaDataName)?.let {
        logger.i { "Importing Metadata" }
        val str = it.inputStream.readAllBytes().decodeToString()
        it.close()
        str
    }

    override suspend fun deletePlaylist(playlistName: String) {
        share?.rm("$playlistName.json")
    }


    private fun getFile(
        path: String,
    ): File? =
        try {
            logger.v { "Getting File at path $path" }
            share?.openFile(
                path,
                accessMask,
                attributes,
                shareAccesses,
                createDisposition,
                createOptions,
            )
        } catch (e: Exception) {
            logger.e(e) { "Error Getting File" }
            null
        }


    private suspend fun writeFile(fileName: String, contents: String): Boolean {
        logger.i { "Writing File" }
        val fileAttributes: MutableSet<FileAttributes> = HashSet()
        fileAttributes.add(FileAttributes.FILE_ATTRIBUTE_NORMAL)
        val createOptions: MutableSet<SMB2CreateOptions> = HashSet()
        createOptions.add(SMB2CreateOptions.FILE_RANDOM_ACCESS)

        try {

            logger.i { "Trying to open a file $fileName" }
            val file = share?.openFile(
                fileName,
                setOf(AccessMask.GENERIC_ALL),
                fileAttributes,
                SMB2ShareAccess.ALL,
                SMB2CreateDisposition.FILE_OVERWRITE_IF,
                createOptions
            )
            file?.let {
                logger.i { "Got the file, writing contents" }
                val oStream: OutputStream = it.outputStream
                oStream.write(contents.toByteArray())
                oStream.flush()
                oStream.close()
                logger.i { "Got the file, writing contents" }
                it.close()
                return true
            }
        } catch (e: Exception) {
            logger.e(e) { "Failed to save to file" }
        }
        return false
    }
}
