package com.kevinschildhorn.fotopresenter.data.network

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
                listOf<NetworkDirectoryDetails>(
                    MockNetworkDirectoryDetails(fullPath = "NewDirectory", id = 1),
                    MockNetworkDirectoryDetails(fullPath = "Peeng.png", id = 2),
                    MockNetworkDirectoryDetails(fullPath = "Jaypeg.jpg", id = 3),
                    MockNetworkDirectoryDetails(fullPath = "textFile.txt", id = 4),
                ),
            "Directories" to
                listOf<NetworkDirectoryDetails>(
                    MockNetworkDirectoryDetails(fullPath = "Directories/NewDirectory", id = 1),
                    MockNetworkDirectoryDetails(fullPath = "Directories/NewDirectory2", id = 2),
                ),
            "Photos" to
                listOf<NetworkDirectoryDetails>(
                    MockNetworkDirectoryDetails(fullPath = "Photos/Peeng.png", id = 2),
                    MockNetworkDirectoryDetails(fullPath = "Photos/Jaypeg.jpg", id = 3),
                    MockNetworkDirectoryDetails(fullPath = "Photos/textFile.txt", id = 4),
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
        print("Connecting\n")
        if (credentials.hostname == "throw") {
            print("Exception Occured\n")
            throw Exception()
        }
        connected = credentials == successLoginCredentials
        print("Is Connected $connected\n")
        return connected
    }

    override suspend fun disconnect() {
        connected = false
    }

    override suspend fun getDirectoryContents(path: String): List<NetworkDirectoryDetails> {
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
