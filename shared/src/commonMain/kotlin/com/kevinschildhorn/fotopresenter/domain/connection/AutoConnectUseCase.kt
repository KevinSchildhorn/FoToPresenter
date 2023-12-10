package com.kevinschildhorn.fotopresenter.domain.connection

import co.touchlab.kermit.Logger
import com.kevinschildhorn.fotopresenter.data.network.NetworkHandler
import com.kevinschildhorn.fotopresenter.data.repositories.CredentialsRepository

/**
Automatically connect to the server using saved credentials
 **/
class AutoConnectUseCase(
    private val client: NetworkHandler,
    private val repository: CredentialsRepository,
    private val logger: Logger,
) {
    suspend operator fun invoke(): Boolean =
        try {
            logger.i { "Fetching Credentials" }
            val credentials = repository.fetchCredentials()
            logger.i { "Connecting to the client with auto-connect:${credentials.shouldAutoConnect}" }
            client.connect(credentials)
        } catch (e: Exception) {
            logger.e(e) { "Could not connect" }
            false
        }
}
