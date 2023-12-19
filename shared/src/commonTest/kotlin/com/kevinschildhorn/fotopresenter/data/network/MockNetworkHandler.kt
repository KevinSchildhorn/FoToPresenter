package com.kevinschildhorn.fotopresenter.data.network

import com.kevinschildhorn.fotopresenter.data.LoginCredentials
import com.kevinschildhorn.fotopresenter.ui.shared.SharedImage

object MockNetworkHandler : NetworkHandler {
    private val successLoginCredentials =
        LoginCredentials(
            "192.168.1.1",
            "admin",
            "password",
            "Public",
            shouldAutoConnect = false,
        )

    val photoDirectoryId = 5

    private val networkContents =
        mapOf(
            "" to
                listOf<NetworkDirectoryDetails>(
                    DefaultNetworkDirectoryDetails(fullPath = "Photos", id = photoDirectoryId),
                    DefaultNetworkDirectoryDetails(fullPath = "NewDirectory", id = 1),
                    DefaultNetworkDirectoryDetails(fullPath = "Peeng.png", id = 75),
                    DefaultNetworkDirectoryDetails(fullPath = "Jaypeg.jpg", id = 3),
                    DefaultNetworkDirectoryDetails(fullPath = "textFile.txt", id = 4),
                ),
            "Directories" to
                listOf<NetworkDirectoryDetails>(
                    DefaultNetworkDirectoryDetails(fullPath = "Directories/NewDirectory", id = 1),
                    DefaultNetworkDirectoryDetails(fullPath = "Directories/NewDirectory2", id = 2),
                ),
            "Photos" to
                listOf<NetworkDirectoryDetails>(
                    DefaultNetworkDirectoryDetails(fullPath = "Photos/Peeng2.png", id = 2),
                    DefaultNetworkDirectoryDetails(fullPath = "Photos/Jaypeg2.jpg", id = 3),
                    DefaultNetworkDirectoryDetails(fullPath = "Photos/textFile2.txt", id = 4),
                    DefaultNetworkDirectoryDetails(fullPath = "Photos/SubPhotos", id = 5),
                ),
            "Photos/SubPhotos" to
                listOf<NetworkDirectoryDetails>(
                    DefaultNetworkDirectoryDetails(
                        fullPath = "Photos/SubPhotos/Peeng3.png",
                        id = 2,
                    ),
                    DefaultNetworkDirectoryDetails(
                        fullPath = "Photos/SubPhotos/Jaypeg3.jpg",
                        id = 3,
                    ),
                    DefaultNetworkDirectoryDetails(
                        fullPath = "Photos/SubPhotos/textFile3.txt",
                        id = 4,
                    ),
                ),
        )

    private val successImageName: String = "Photos/Success.png"

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
        print("Getting Directory Contents ${path}\n")

        return networkContents[path] ?: emptyList()
    }

    override suspend fun openDirectory(directoryName: String): String? {
        print("Opening Directory ${directoryName}\n")
        if (networkContents.containsKey(directoryName)) {
            return directoryName
        }
        return null
    }

    override suspend fun openImage(imageName: String): SharedImage? {
        print("Opening Image ${imageName}\n")
        if (imageName == successImageName) {
            throw Exception("Success") // TODO: This is messy, but SharedImageIs expect
        }
        return null
    }
}
