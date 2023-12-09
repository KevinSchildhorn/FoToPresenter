package com.kevinschildhorn.fotopresenter.domain.connection

import co.touchlab.kermit.Logger
import com.kevinschildhorn.fotopresenter.data.network.NetworkHandler
import com.kevinschildhorn.fotopresenter.data.repositories.CredentialsRepository

/**
Logging out of App
 **/
class DisconnectFromServerUseCase(
    private val credentialsRepository: CredentialsRepository,
    private val networkHandler: NetworkHandler,
    private val logger: Logger,
) {
    suspend operator fun invoke() {
        logger.i { "Disconnecting" }
        networkHandler.disconnect()
        logger.d { "Clearing Auto-connect" }
        credentialsRepository.clearAutoConnect()
    }
}
