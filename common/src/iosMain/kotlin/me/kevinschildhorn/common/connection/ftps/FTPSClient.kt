package me.kevinschildhorn.common.connection.ftps

import me.kevinschildhorn.common.connection.DirectoryInfo

actual object FTPSClient {
    actual val isConnected: Boolean = false
    actual var currentDirectory: DirectoryInfo? = nil

    // Connection
    actual suspend fun connect(
        hostname: String,
        port: Int,
        username: String,
        password: String
    ): Boolean = false

    actual fun disconnect() {}

    // Navigation
    actual suspend fun navigateForward(directoryName: String): Boolean = false
    actual suspend fun navigateBack(): Boolean = false
}