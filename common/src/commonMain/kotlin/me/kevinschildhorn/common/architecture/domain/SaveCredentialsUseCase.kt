package me.kevinschildhorn.common.architecture.domain

import co.touchlab.kermit.Logger
import me.kevinschildhorn.common.architecture.data.repositories.CredentialsRepository

/**
Saves sign in credentials to the repository
 **/
class SaveCredentialsUseCase(
    private val repository: CredentialsRepository,
    private val logger: Logger,
) {

    operator fun invoke(
        hostname: String,
        port: Int,
        username: String,
        password: String,
        autoConnect: Boolean
    ): Boolean =
        try {
            logger.i { "Saving Credentials" }
            repository.saveCredentials(hostname, port, username, password, autoConnect)
            true
        } catch (e: Exception) {
            false
        }
}
