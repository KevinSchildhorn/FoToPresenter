package me.kevinschildhorn.common.architecture.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import me.kevinschildhorn.common.architecture.data.repositories.CredentialsRepository

/**
Saves sign in credentials to the repository
 **/
class SaveCredentialsUseCase(
    private val repository: CredentialsRepository,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default,
) {

    operator fun invoke(address: String, username: String, password: String): Boolean =
        try {
            repository.saveCredentials(address, username, password)
            true
        } catch (e: Exception) {
            false
        }
}
