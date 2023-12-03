package com.kevinschildhorn.fotopresenter.domain

import com.kevinschildhorn.fotopresenter.data.LoginCredentials
import com.kevinschildhorn.fotopresenter.data.network.NetworkHandler

/**
Connect to Server using FTPS
 **/
class ConnectToServerUseCase(
    private val client: NetworkHandler
) {

    suspend operator fun invoke(credentials: LoginCredentials): Boolean =
        try {
            client.connect(credentials)
        } catch (e: Exception) {
            false
        }
}