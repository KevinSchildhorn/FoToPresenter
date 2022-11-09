package me.kevinschildhorn.common.layers.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import me.kevinschildhorn.common.layers.data.repositories.CredentialsRepository
import org.koin.core.component.KoinComponent

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