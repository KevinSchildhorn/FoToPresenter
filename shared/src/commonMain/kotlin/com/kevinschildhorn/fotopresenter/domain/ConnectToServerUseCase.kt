package com.kevinschildhorn.fotopresenter.domain

import com.kevinschildhorn.fotopresenter.data.LoginCredentials
import com.kevinschildhorn.fotopresenter.data.network.NetworkHandler
import co.touchlab.kermit.Logger

/**
Connect to Server using FTPS
 **/
class ConnectToServerUseCase(
    private val client: NetworkHandler,
    private val logger: Logger,
) {
    suspend operator fun invoke(credentials: LoginCredentials): Boolean =
        try {
            logger.i { "Connecting to Client" }
            client.connect(credentials)
        } catch (e: Exception) {
            logger.e(e) { "Something went wrong" }
            false
        }
}
