package com.kevinschildhorn.fotopresenter.data.network

import androidx.compose.ui.graphics.ImageBitmap
import com.kevinschildhorn.fotopresenter.data.LoginCredentials
import com.kevinschildhorn.fotopresenter.ui.SharedImage

object MockNetworkHandler : NetworkHandler {
    private val successLoginCredentials =
        LoginCredentials(
            "192.168.1.1",
            "admin",
            "password",
            "Public",
            shouldAutoConnect = false,
        )

    private val networkContents =
        mapOf(
            "" to
                listOf<NetworkDirectory>(
                    MockNetworkDirectory(fullPath = "NewDirectory", id = 1),
                    MockNetworkDirectory(fullPath = "Peeng.png", id = 2),
                    MockNetworkDirectory(fullPath = "Jaypeg.jpg", id = 3),
                    MockNetworkDirectory(fullPath = "textFile.txt", id = 4),
                ),
            "Directories" to
                listOf<NetworkDirectory>(
                    MockNetworkDirectory(fullPath = "Directories/NewDirectory", id = 1),
                    MockNetworkDirectory(fullPath = "Directories/NewDirectory2", id = 2),
                ),
            "Photos" to
                listOf<NetworkDirectory>(
                    MockNetworkDirectory(fullPath = "Photos/Peeng.png", id = 2),
                    MockNetworkDirectory(fullPath = "Photos/Jaypeg.jpg", id = 3),
                    MockNetworkDirectory(fullPath = "Photos/textFile.txt", id = 4),
                ),
        )

    private val successImageName: String = "Photos/Peeng.png"

    private var connected: Boolean = false
    override val isConnected: Boolean
        get() = connected

    suspend fun connectSuccessfully() {
        connect(successLoginCredentials)
    }

    override suspend fun connect(credentials: LoginCredentials): Boolean {
        if (credentials.hostname == "throw") throw Exception()
        connected = credentials == successLoginCredentials
        return connected
    }

    override suspend fun disconnect() {
        connected = false
    }

    override suspend fun getDirectoryContents(path: String): List<NetworkDirectory> {
        return networkContents[path] ?: emptyList()
    }

    override suspend fun openDirectory(directoryName: String): String? {
        if (networkContents.containsKey(directoryName)) {
            return directoryName
        }
        return null
    }

    override suspend fun openImage(imageName: String): SharedImage? {
        if (imageName == successImageName) {
            throw Exception("Success") // TODO: This is messy, but SharedImageIs expect
        }
        return null
    }
}
