package com.kevinschildhorn.fotopresenter.domain

import co.touchlab.kermit.Logger
import com.kevinschildhorn.fotopresenter.data.network.NetworkHandler
import com.kevinschildhorn.fotopresenter.data.repositories.CredentialsRepository

/**
Logging out of App
 **/
class LogoutUseCase(
    private val credentialsRepository: CredentialsRepository,
    private val networkHandler: NetworkHandler,
    private val logger: Logger,
) {
    suspend operator fun invoke() {
        networkHandler.disconnect()
        credentialsRepository.clearAutoConnect()
    }
}
