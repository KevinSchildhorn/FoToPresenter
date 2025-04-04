package com.kevinschildhorn.fotopresenter.domain.connection

import co.touchlab.kermit.Logger
import com.kevinschildhorn.fotopresenter.data.login.LoginCredentials
import com.kevinschildhorn.fotopresenter.data.network.NetworkHandler

/**
Connect to Server using The [NetworkHandler]
 **/
class ConnectToServerUseCase(
    private val client: NetworkHandler,
    private val logger: Logger,
) {
    suspend operator fun invoke(credentials: LoginCredentials): Boolean =
        try {
            logger.i { "Connecting to Client ${credentials.hostname}" }
            client.connect(credentials)
        } catch (e: Exception) {
            logger.e(e) { "Something went wrong" }
            false
        }
}
