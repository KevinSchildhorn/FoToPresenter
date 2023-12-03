package com.kevinschildhorn.fotopresenter.data.network

import androidx.compose.ui.graphics.ImageBitmap
import com.kevinschildhorn.fotopresenter.data.LoginCredentials

interface NetworkHandler {
    @Throws(Exception::class)
    suspend fun connect(credentials: LoginCredentials): Boolean
    fun openDirectory(directoryName: String)
    fun openImage(imageName: String): ImageBitmap?
}