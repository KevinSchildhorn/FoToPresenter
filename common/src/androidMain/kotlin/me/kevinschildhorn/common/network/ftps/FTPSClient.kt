package me.kevinschildhorn.common.network.ftps

import co.touchlab.kermit.Logger
import me.kevinschildhorn.common.extensions.isPhoto
import me.kevinschildhorn.common.network.DirectoryInformation
import org.apache.commons.net.ftp.FTP
import org.apache.commons.net.ftp.FTPReply
import org.apache.commons.net.ftp.FTPSClient

actual object FTPSClient {

    private val client = FTPSClient()
    private val logger = Logger.withTag("FTPSClient")

    actual val isConnected: Boolean
        get() = client.isConnected

    actual val currentDirectory: DirectoryInformation?
        get() = try {
            DirectoryInformation(
                path = client.printWorkingDirectory(),
                directories = client.listDirectories().asList().filter { it.isDirectory },
                files = client.listFiles().asList().filter { it.isFile }.filter { it.isPhoto }
            )
        } catch (e: Exception) {
            null
        }

    //region Connection

    actual suspend fun connect(
        hostname: String,
        port: Int,
        username: String,
        password: String
    ): Boolean {
        try {
            if (!client.isConnected) {
                logger.i { "Connecting to host:$hostname port:$port" }
                client.connect(hostname, port)

                if (!FTPReply.isPositiveCompletion(client.replyCode)) {
                    logger.w { "Connection failed" }
                    disconnect()
                    return false
                }
            }

            client.controlEncoding = "UTF-8"
            client.setFileType(FTP.BINARY_FILE_TYPE)
            client.enterLocalPassiveMode()

            logger.i { "Logging In" }
            val loggedIn = client.login(username, password)
            if (!loggedIn) {
                logger.w { "Couldn't Login" }
                client.disconnect()
            }
            return loggedIn
        } catch (e: Exception) {
            logger.e { e.localizedMessage ?: e.toString() }
        }
        return false
    }

    actual fun disconnect() {
        try {
            client.logout()
            client.disconnect()
        } catch (e: Exception) {
            logger.e { e.localizedMessage ?: e.toString() }
        }
    }

    //endregion

    //region Navigation

    actual suspend fun navigateForward(directoryName: String): Boolean {
        try {
            return client.changeWorkingDirectory(directoryName)
        } catch (e: Exception) {
            logger.e { e.localizedMessage ?: e.toString() }
        }
        return false
    }

    actual suspend fun navigateBack(): Boolean {
        try {
            return client.changeToParentDirectory()
        } catch (e: Exception) {
            logger.e { e.localizedMessage ?: e.toString() }
        }
        return false
    }

    //endregion
}
