package com.kevinschildhorn.fotopresenter.domain

import com.kevinschildhorn.fotopresenter.data.LoginCredentials

/**
Connect to Server
 **/
class ConnectToServerUseCase() {
    suspend operator fun invoke(
        hostname: String,
        port: Int,
        username: String,
        password: String
    ): Boolean =
        try {
            //FTPSClient.connect(hostname, port, username, password)
            true
        } catch (e: Exception) {
            false
        }

    suspend operator fun invoke(credentials: LoginCredentials): Boolean =
        try {
            /*
            FTPSClient.connect(
                credentials.hostname,
                credentials.port.toInt(),
                credentials.username,
                credentials.password
            )*/
            true
        } catch (e: Exception) {
            false
        }
}