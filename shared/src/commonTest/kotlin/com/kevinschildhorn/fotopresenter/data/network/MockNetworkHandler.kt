package com.kevinschildhorn.fotopresenter.data.network

import androidx.compose.ui.graphics.ImageBitmap
import com.kevinschildhorn.fotopresenter.data.LoginCredentials

class MockNetworkHandler : NetworkHandler {
    private val successLoginCredentials =
        LoginCredentials(
            "192.168.1.1",
            "admin",
            "password",
            "Public",
            shouldAutoConnect = false,
        )

    private val successDirectory: String = "photos"
    private val successImageName: String = "image.jpg"

    @Throws(Exception::class)
    override suspend fun connect(credentials: LoginCredentials): Boolean {
        if (credentials.hostname == "throw") throw Exception()
        return credentials == successLoginCredentials
    }

    override suspend fun openDirectory(directoryName: String) {
        if (directoryName == successDirectory) {
            print("")
        }
    }

    override suspend fun openImage(imageName: String): ImageBitmap? {
        if (imageName == successImageName) {
            print("")
        }
        return null
    }
}
