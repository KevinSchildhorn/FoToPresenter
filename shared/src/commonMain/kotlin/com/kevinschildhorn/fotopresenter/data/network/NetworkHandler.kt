package com.kevinschildhorn.fotopresenter.data.network

import androidx.compose.ui.graphics.ImageBitmap
import com.kevinschildhorn.fotopresenter.data.LoginCredentials
import kotlin.coroutines.cancellation.CancellationException

interface NetworkHandler {
    @Throws(CancellationException::class, CancellationException::class)
    suspend fun connect(credentials: LoginCredentials): Boolean

    @Throws(NetworkHandlerException::class, CancellationException::class)
    suspend fun getDirectoryContents(): List<NetworkDirectory>

    @Throws(NetworkHandlerException::class, CancellationException::class)
    suspend fun openDirectory(directoryName: String)

    @Throws(NetworkHandlerException::class, CancellationException::class)
    suspend fun openImage(imageName: String): ImageBitmap?
}

class NetworkHandlerException: Exception {
    constructor(error:NetworkHandlerError) : super(error.message)
    constructor(message: String) : super(message)
}

enum class NetworkHandlerError(val message: String) {
    NOT_CONNECTED("The Network Handler is not Connected")
}