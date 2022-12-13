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

    operator fun invoke(address: String, username: String, password: String): Boolean =
        try {
            logger.i { "Saving Credentials" }
            repository.saveCredentials(address, username, password)
            true
        } catch (e: Exception) {
            false
        }
}
