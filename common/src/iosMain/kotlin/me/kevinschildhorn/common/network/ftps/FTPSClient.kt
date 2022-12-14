package me.kevinschildhorn.common.network.ftps

import me.kevinschildhorn.common.network.DirectoryInformation

actual object FTPSClient {
    actual val isConnected: Boolean = false
    actual val currentDirectory: DirectoryInformation?
        get() = null

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
