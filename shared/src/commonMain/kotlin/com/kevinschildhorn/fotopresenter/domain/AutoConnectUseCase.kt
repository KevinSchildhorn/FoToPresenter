package com.kevinschildhorn.fotopresenter.domain

import com.kevinschildhorn.shared.architecture.data.repositories.CredentialsRepository

/**
Connect to Server using FTPS and saved credentials
 **/
class AutoConnectUseCase(
    private val repository: CredentialsRepository
) {
    suspend operator fun invoke(): Boolean {
        val credentials = repository.fetchCredentials()
        if (!credentials.isComplete) return false

        val connectToServerUseCase = ConnectToServerUseCase()
        return connectToServerUseCase(credentials)
    }
}
