package me.kevinschildhorn.common.layers.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import me.kevinschildhorn.common.layers.data.repositories.CredentialsRepository

class SaveCredentialsUseCase(
    val repository: CredentialsRepository = CredentialsRepository(),
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default,
) {

    operator fun invoke(username: String, password: String): Boolean {
        return true
    }
}