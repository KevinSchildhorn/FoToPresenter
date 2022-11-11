package me.kevinschildhorn.common.layers.data.repositories

import me.kevinschildhorn.common.layers.data.data.SignInCredentials
import me.kevinschildhorn.common.layers.data.datasources.CredentialsDataSource

/**
Fetches [SignInCredentials] from [CredentialsDataSource] to be used to sign into an FTP server
 **/
class CredentialsRepository(
    private val dataSource: CredentialsDataSource,
) {

    fun fetchCredentials(): SignInCredentials =
        SignInCredentials(
            address = dataSource.address,
            username = dataSource.username,
            password = dataSource.password
        )

    fun saveCredentials(address: String, username: String, password: String) {
        dataSource.address = address
        dataSource.username = username
        dataSource.password = password
    }
}
