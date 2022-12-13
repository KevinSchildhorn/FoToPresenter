package me.kevinschildhorn.common.ftps

import android.util.Log
import org.apache.commons.net.ftp.FTP
import org.apache.commons.net.ftp.FTPReply
import org.apache.commons.net.ftp.FTPSClient

actual class FTPSClient {

    val client = FTPSClient()

    actual val isConnected: Boolean
        get() = client.isConnected

    //region Connection

    actual suspend fun connect(
        host: String,
        port: Int,
        username: String,
        password: String
    ): Boolean {
        try {
            if (!client.isConnected) {
                client.connect(host, port)

                if (!FTPReply.isPositiveCompletion(client.replyCode)) {
                    disconnect()
                    return false
                }
            }

            client.controlEncoding = "UTF-8"
            client.setFileType(FTP.BINARY_FILE_TYPE)
            client.enterLocalPassiveMode()

            val loggedIn = client.login(username, password)
            if (!loggedIn) {
                client.disconnect()
            }
            return loggedIn
        } catch (e: Exception) {
            Log.i("", "Error")
        }
        return false
    }

    actual fun disconnect() {
        try {
            client.logout()
            client.disconnect()
        } catch (e: Exception) {
            Log.i("", "Error")
        }
    }

    //endregion

    //region Navigation

    actual suspend fun navigateForward(directoryName: String): Boolean {
        try {
            return client.changeWorkingDirectory(directoryName)
        } catch (e: Exception) {
            Log.i("", "Error")
        }
        return false
    }

    actual suspend fun navigateBack(): Boolean {
        try {
            return client.changeToParentDirectory()
        } catch (e: Exception) {
            Log.i("", "Error")
        }
        return false
    }

    //endregion
}