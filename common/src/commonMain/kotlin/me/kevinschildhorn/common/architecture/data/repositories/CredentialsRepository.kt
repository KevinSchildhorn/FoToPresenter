package me.kevinschildhorn.common.architecture.data.repositories

import me.kevinschildhorn.common.architecture.data.data.SignInCredentials
import me.kevinschildhorn.common.architecture.data.datasources.CredentialsDataSource

/**
Fetches [SignInCredentials] from [CredentialsDataSource] to be used to sign into an FTP server
 **/
class CredentialsRepository(
    private val dataSource: CredentialsDataSource,
) {

    fun fetchCredentials(): SignInCredentials =
        SignInCredentials(
            address = dataSource.address,
            port = dataSource.port.toString(),
            username = dataSource.username,
            password = dataSource.password
        )

    fun saveCredentials(address: String, username: String, password: String, autoConnect: Boolean) {
        dataSource.address = address
        dataSource.username = username
        dataSource.password = password
        dataSource.autoConnect = autoConnect
    }
}
