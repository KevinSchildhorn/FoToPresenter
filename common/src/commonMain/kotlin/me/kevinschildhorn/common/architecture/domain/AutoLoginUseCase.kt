package me.kevinschildhorn.common.architecture.domain

import me.kevinschildhorn.common.architecture.data.repositories.CredentialsRepository

/**
Connect to Server using FTPS and saved credentials
 **/
class AutoLoginUseCase(
    private val repository: CredentialsRepository
) {
    suspend operator fun invoke(): Boolean {
        val credentials = repository.fetchCredentials()
        if (!credentials.isComplete) return false

        val connectToServerUseCase = ConnectToServerUseCase()
        return connectToServerUseCase(credentials)
    }
}
