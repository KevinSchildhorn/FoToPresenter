package com.kevinschildhorn.fotopresenter.data.network

import com.kevinschildhorn.fotopresenter.data.LoginCredentials
import com.kevinschildhorn.fotopresenter.ui.SharedImage

interface NetworkHandler {
    val isConnected: Boolean

    suspend fun connect(credentials: LoginCredentials): Boolean

    suspend fun disconnect()

    suspend fun getDirectoryContents(path: String): List<NetworkDirectory>

    suspend fun openDirectory(path: String): String?

    suspend fun openImage(path: String): SharedImage?
}

class NetworkHandlerException : Exception {
    constructor(error: NetworkHandlerError) : super(error.message)
    constructor(message: String) : super(message)
}

enum class NetworkHandlerError(val message: String) {
    NOT_CONNECTED("The Network Handler is not Connected"),
    DIRECTORY_NOT_FOUND("The Directory you selected was not found"),
}
