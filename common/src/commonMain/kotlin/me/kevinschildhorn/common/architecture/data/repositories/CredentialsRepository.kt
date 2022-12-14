package me.kevinschildhorn.common.architecture.data.repositories

import me.kevinschildhorn.common.architecture.data.data.LoginCredentials
import me.kevinschildhorn.common.architecture.data.datasources.CredentialsDataSource

/**
Fetches [LoginCredentials] from [CredentialsDataSource] to be used to sign into an FTP server
 **/
class CredentialsRepository(
    private val dataSource: CredentialsDataSource,
) {

    fun fetchCredentials(): LoginCredentials =
        LoginCredentials(
            hostname = dataSource.hostname,
            port = dataSource.port.toString(),
            username = dataSource.username,
            password = dataSource.password
        )

    fun saveCredentials(hostname: String, username: String, password: String, autoConnect: Boolean) {
        dataSource.hostname = hostname
        dataSource.username = username
        dataSource.password = password
        dataSource.autoConnect = autoConnect
    }
}
