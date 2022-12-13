package me.kevinschildhorn.common.connection.ftps

expect object FTPSClient {

    val isConnected: Boolean

    // Connection
    suspend fun connect(hostname: String, port: Int, username: String, password: String): Boolean
    fun disconnect()

    // Navigation
    suspend fun navigateForward(directoryName: String): Boolean
    suspend fun navigateBack(): Boolean
}
