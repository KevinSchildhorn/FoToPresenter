package me.kevinschildhorn.common.connection.ftps

import me.kevinschildhorn.common.connection.DirectoryInformation

expect object FTPSClient {

    val isConnected: Boolean
    val currentDirectory: DirectoryInformation?

    // Connection
    suspend fun connect(hostname: String, port: Int, username: String, password: String): Boolean
    fun disconnect()

    // Navigation
    suspend fun navigateForward(directoryName: String): Boolean
    suspend fun navigateBack(): Boolean
}
