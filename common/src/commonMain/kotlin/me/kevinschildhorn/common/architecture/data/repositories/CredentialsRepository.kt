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
            port = dataSource.port,
            username = dataSource.username,
            password = dataSource.password
        )

    fun saveCredentials(hostname: String, port: Int, username: String, password: String, autoConnect: Boolean) {
        dataSource.hostname = hostname
        dataSource.port = port
        dataSource.username = username
        dataSource.password = password
        dataSource.autoConnect = autoConnect
    }
}
