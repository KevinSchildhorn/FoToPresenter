package me.kevinschildhorn.common.architecture.domain

import me.kevinschildhorn.common.architecture.data.data.SignInCredentials
import me.kevinschildhorn.common.network.ftps.FTPSClient

/**
Connect to Server using FTPS
 **/
class ConnectToServerUseCase() {
    suspend operator fun invoke(
        hostname: String,
        port: Int,
        username: String,
        password: String
    ): Boolean =
        try {
            FTPSClient.connect(hostname, port, username, password)
        } catch (e: Exception) {
            false
        }

    suspend operator fun invoke(credentials: SignInCredentials): Boolean =
        try {
            FTPSClient.connect(
                credentials.address,
                credentials.port.toInt(),
                credentials.username,
                credentials.password
            )
        } catch (e: Exception) {
            false
        }
}
