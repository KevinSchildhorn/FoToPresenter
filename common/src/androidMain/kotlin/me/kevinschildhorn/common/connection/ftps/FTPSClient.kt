package me.kevinschildhorn.common.connection.ftps

import co.touchlab.kermit.Logger
import org.apache.commons.net.ftp.FTP
import org.apache.commons.net.ftp.FTPReply
import org.apache.commons.net.ftp.FTPSClient
import org.koin.java.KoinJavaComponent.inject

actual object FTPSClient {

    private val client = FTPSClient()
    private val logger = Logger.withTag("FTPSClient")

    actual val isConnected: Boolean
        get() = client.isConnected

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
            logger.e { e.localizedMessage ?: "Exception Connecting"}
        }
        return false
    }

    actual fun disconnect() {
        try {
            client.logout()
            client.disconnect()
        } catch (e: Exception) {
            logger.e { e.localizedMessage ?: "Exception Disconnecting" }
        }
    }

    //endregion

    //region Navigation

    actual suspend fun navigateForward(directoryName: String): Boolean {
        try {
            return client.changeWorkingDirectory(directoryName)
        } catch (e: Exception) {
            logger.e { e.localizedMessage ?: "Exception Navigating Forward" }
        }
        return false
    }

    actual suspend fun navigateBack(): Boolean {
        try {
            return client.changeToParentDirectory()
        } catch (e: Exception) {
            logger.e { e.localizedMessage ?: "Navigate Back" }
        }
        return false
    }

    //endregion
}