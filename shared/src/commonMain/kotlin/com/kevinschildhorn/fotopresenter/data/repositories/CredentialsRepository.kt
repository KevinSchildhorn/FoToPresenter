package com.kevinschildhorn.fotopresenter.data.repositories

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
            username = dataSource.username,
            password = dataSource.password,
            sharedFolder = dataSource.sharedFolder,
            shouldAutoConnect = dataSource.shouldAutoConnect,
        )

    fun saveCredentials(
        hostname: String,
        username: String,
        password: String,
        sharedFolder: String,
        shouldAutoConnect: Boolean,
    ) {
        dataSource.hostname = hostname
        dataSource.username = username
        dataSource.password = password
        dataSource.sharedFolder = sharedFolder
        dataSource.shouldAutoConnect = shouldAutoConnect
    }

    fun clearAutoConnect() {
        dataSource.shouldAutoConnect = false
    }
}
