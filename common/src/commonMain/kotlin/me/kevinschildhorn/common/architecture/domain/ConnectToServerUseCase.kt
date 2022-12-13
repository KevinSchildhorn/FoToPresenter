package me.kevinschildhorn.common.architecture.domain

import me.kevinschildhorn.common.connection.ftps.FTPSClient

/**
Connect to Server using FTPS
 **/
class ConnectToServerUseCase() {
    suspend operator fun invoke(hostname: String, port: Int, username: String, password: String): Boolean =
        try {
            FTPSClient.connect(hostname, port, username, password)
        } catch (e: Exception) {
            false
        }
}