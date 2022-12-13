package me.kevinschildhorn.common.network.ftps

import me.kevinschildhorn.common.network.DirectoryInformation

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
