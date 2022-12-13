package me.kevinschildhorn.common.ftps

expect class FTPSClient() {
    val isConnected: Boolean

    // Connection
    suspend fun connect(host: String, port: Int, username: String, password: String): Boolean
    fun disconnect()

    // Navigation
    suspend fun navigateForward(directoryName: String): Boolean
    suspend fun navigateBack(): Boolean
}