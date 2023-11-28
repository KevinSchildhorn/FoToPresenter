package com.kevinschildhorn.fotopresenter.data.datasources.repositories

import com.kevinschildhorn.fotopresenter.data.LoginCredentials
import com.kevinschildhorn.fotopresenter.data.datasources.CredentialsDataSource

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