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
import com.kevinschildhorn.fotopresenter.data.Path
import com.kevinschildhorn.fotopresenter.data.login.LoginCredentials
import com.kevinschildhorn.fotopresenter.ui.shared.SharedImage
import java.io.OutputStream

object SMBJHandler : NetworkHandler {
    private val client = SMBClient()
    private var connection: Connection? = null
    private var session: Session? = null
    private var share: DiskShare? = null
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
            logger.d { "Connecting to location ${credentials.hostname}" }
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

    override suspend fun getDirectoryDetails(path: Path): NetworkDirectoryDetails? {
        logger.i { "Getting Directory Details for $path" }
        share?.getFileInformation(path.toString())?.let {
            return DefaultNetworkDirectoryDetails(
                id = it.internalInformation.indexNumber,
                fullPath = path,
            )
        } ?: run {
            return null
        }
    }

    override suspend fun getDirectoryContents(path: Path): List<NetworkDirectoryDetails> {
        logger.d { "Getting Directory Contents for '$path'" }
        return share?.list(path.toString())?.map {
            SMBJNetworkDirectoryDetails(
                it,
                fullPath = path.addPath(it.fileName),
            )
        } ?: emptyList()
    }

    // Gets a handle to a directory in the given path
    override suspend fun openDirectory(path: Path): Path? {
        logger.i { "Opening Directory $path" }
        val result =
            share?.openDirectory(
                path.toString(),
                accessMask,
                attributes,
                shareAccesses,
                createDisposition,
                createOptions,
            )
        return if (result != null) Path(result.path) else null
    }

    override suspend fun getSharedImage(path: Path): SharedImage? =
        getFile(path)?.let { file ->
            val byteArray = file.inputStream.readAllBytes()
            file.close()
            val sharedImage = SharedImage(byteArray)
            sharedImage
        } ?: run {
            null
        }

    @Throws
    override suspend fun setSharedImage(
        path: Path,
        sharedImage: SharedImage,
    ): Boolean = writeFile(fileName = path.toString(), contents = sharedImage.byteArray)

    override suspend fun folderExists(path: Path): Boolean? {
        return share?.folderExists(path.toString())
    }

    override suspend fun disconnect() {
        share?.close()
        session?.close()
        connection?.close(true)
        share = null
        session = null
        connection = null
    }

    override suspend fun savePlaylist(
        playlistName: String,
        json: String,
    ): Boolean = writeFile(fileName = "$playlistName.playlist.json", contents = json.toByteArray())

    override suspend fun getPlaylists(): List<String> =
        getDirectoryContents(Path.EMPTY)
            .filter { it.fileExtension == "json" }
            .filter { !it.fileName.contains(".playlist") }
            .mapNotNull { getFile(it.fullPath) }
            .map {
                val str = it.inputStream.readAllBytes().decodeToString()
                it.close()
                str
            }

    override suspend fun deletePlaylist(playlistName: String) {
        share?.rm("$playlistName.json")
    }

    private fun getFile(path: Path): File? =
        try {
            logger.v { "Getting File at path $path" }
            share?.openFile(
                path.toString(),
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

    private fun writeFile(
        fileName: String,
        contents: ByteArray,
    ): Boolean {
        logger.i { "Writing File" }
        val fileAttributes: MutableSet<FileAttributes> = HashSet()
        fileAttributes.add(FileAttributes.FILE_ATTRIBUTE_NORMAL)
        val createOptions: MutableSet<SMB2CreateOptions> = HashSet()
        createOptions.add(SMB2CreateOptions.FILE_RANDOM_ACCESS)

        try {
            logger.i { "Trying to open a file $fileName" }
            val file =
                share?.openFile(
                    fileName,
                    setOf(AccessMask.GENERIC_ALL),
                    fileAttributes,
                    SMB2ShareAccess.ALL,
                    SMB2CreateDisposition.FILE_OVERWRITE_IF,
                    createOptions,
                )
            file?.let {
                logger.i { "Got the file, writing contents" }
                val oStream: OutputStream = it.outputStream
                oStream.write(contents)
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
