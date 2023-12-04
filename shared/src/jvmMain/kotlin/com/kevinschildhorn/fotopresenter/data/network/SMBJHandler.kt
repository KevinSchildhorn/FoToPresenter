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

    override suspend fun connect(credentials: LoginCredentials): Boolean {
        try {
            with(credentials) {
                connection = client.connect(hostname)
                val context =
                    AuthenticationContext(
                        username,
                        password.toCharArray(),
                        null,
                    )
                val session: Session? = connection?.authenticate(context)
                share = session?.connectShare("Public") as? DiskShare
                if (share == null) {
                    closeConnection()
                    return false
                }
                return true
            }
        } catch (e: Exception) {
            closeConnection()
            return false
        }
    }

    override fun openDirectory(directoryName: String) {
        share?.openDirectory(
            directoryName,
            accessMask,
            attributes,
            shareAccesses,
            createDisposition,
            createOptions,
        )
    }

    override fun openImage(imageName: String): ImageBitmap? {
        val file = share?.openFile(
            imageName,
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

    private fun closeConnection() {
        share?.close()
        session?.close()
        connection?.close(true)
        share = null
        session = null
        connection = null
    }
}
