package com.kevinschildhorn.fotopresenter.domain

import co.touchlab.kermit.Logger
import com.kevinschildhorn.fotopresenter.data.LoginCredentials
import com.kevinschildhorn.fotopresenter.data.repositories.CredentialsRepository

/**
Saves sign in credentials to the repository
 **/
class SaveCredentialsUseCase(
    private val repository: CredentialsRepository,
    private val logger: Logger,
) {

    operator fun invoke(
        loginCredentials: LoginCredentials,
    ): Boolean =
        try {
            with(loginCredentials) {
                logger.i { "Saving Credentials" }
                repository.saveCredentials(
                    hostname = hostname,
                    username = username,
                    password = password,
                    sharedFolder = sharedFolder,
                    shouldAutoConnect = shouldAutoConnect,
                )
            }
            true
        } catch (e: Exception) {
            logger.e(e) { "Error Saving Credentials" }
            false
        }
}