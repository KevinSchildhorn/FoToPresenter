package com.kevinschildhorn.fotopresenter.data.network

import androidx.compose.ui.graphics.ImageBitmap
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
import com.kevinschildhorn.fotopresenter.data.LoginCredentials
import com.kevinschildhorn.fotopresenter.extension.addPath

object SMBJHandler : NetworkHandler {
    private val client = SMBClient()
    private var connection: Connection? = null
    private var session: Session? = null
    private var share: DiskShare? = null

    private val accessMask: Set<AccessMask> = setOf(AccessMask.FILE_READ_DATA)
    private val attributes: Set<FileAttributes> = setOf(FileAttributes.FILE_ATTRIBUTE_NORMAL)
    private val shareAccesses: Set<SMB2ShareAccess> = setOf(SMB2ShareAccess.FILE_SHARE_READ)
    private val createDisposition: SMB2CreateDisposition = SMB2CreateDisposition.FILE_OPEN
    private val createOptions: Set<SMB2CreateOptions> =
        setOf(SMB2CreateOptions.FILE_NON_DIRECTORY_FILE)

    override val isConnected: Boolean
        get() = share != null

    override suspend fun connect(credentials: LoginCredentials): Boolean {
        try {
            with(credentials) {
                connection = client.connect(hostname)
                val context = AuthenticationContext(username, password.toCharArray(), null)
                val session: Session? = connection?.authenticate(context)
                share = session?.connectShare(credentials.sharedFolder) as? DiskShare
                if (share == null) {
                    disconnect()
                    return false
                }
            }
        } catch (e: Exception) {
            disconnect()
            return false
        }
        return true
    }

    override suspend fun getDirectoryContents(path: String): List<NetworkDirectory> {
        return share?.list(path)?.map {
            SMBJNetworkDirectory(
                it,
                fullPath = path.addPath(it.fileName),
            )
        } ?: emptyList()
    }

    override suspend fun openDirectory(path: String): String? {
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

    override suspend fun openImage(path: String): ImageBitmap? {
        val file = share?.openFile(
            path,
            accessMask,
            attributes,
            shareAccesses,
            createDisposition,
            createOptions,
        )
        return null
        // val bMap = BitmapFactory.decodeStream(file.inputStream)
        // return bMap
    }

    override suspend fun disconnect() {
        share?.close()
        session?.close()
        connection?.close(true)
        share = null
        session = null
        connection = null
    }
}
