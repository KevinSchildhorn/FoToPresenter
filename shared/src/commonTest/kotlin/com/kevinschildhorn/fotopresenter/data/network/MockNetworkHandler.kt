package com.kevinschildhorn.fotopresenter.data.network

import com.kevinschildhorn.fotopresenter.data.Path
import com.kevinschildhorn.fotopresenter.data.login.LoginCredentials
import com.kevinschildhorn.fotopresenter.ui.shared.SharedImage

object MockNetworkHandler : NetworkHandler {
    private val successLoginCredentials =
        LoginCredentials(
            "192.168.1.1",
            "admin",
            "password",
            "Public",
            shouldAutoConnect = true,
        )

    const val PHOTO_DIRECTORY_ID: Long = 5L

    private val playlists =
        mutableMapOf(
            "Existing" to
                """
                {
                    "id" : 1,
                    "name" : "Existing",
                    "items": [
                        {
                            "id" : 1,
                            "playlistId" : 1,
                            "directoryPath" : "Photos/SubPhotos/Peeng3.png",
                            "directoryId" : 2
                        },
                        {
                            "id" : 2,
                            "playlistId" : 1,
                            "directoryPath" : "Photos/Jaypeg2.jpg",
                            "directoryId" : 3
                        }
                    ]
                }
                """.trimIndent(),
        )
    private val networkContents =
        mapOf(
            Path.EMPTY to
                listOf<NetworkDirectoryDetails>(
                    DefaultNetworkDirectoryDetails(fullPath = Path("Photos"), id = PHOTO_DIRECTORY_ID),
                    DefaultNetworkDirectoryDetails(fullPath = Path("NewDirectory"), id = 1),
                    DefaultNetworkDirectoryDetails(fullPath = Path("Peeng.png"), id = 75),
                    DefaultNetworkDirectoryDetails(fullPath = Path("Jaypeg.jpg"), id = 3),
                    DefaultNetworkDirectoryDetails(fullPath = Path("textFile.txt"), id = 4),
                ),
            Path("Directories") to
                listOf<NetworkDirectoryDetails>(
                    DefaultNetworkDirectoryDetails(
                        fullPath = Path("Directories/NewDirectory"),
                        id = 1,
                    ),
                    DefaultNetworkDirectoryDetails(
                        fullPath = Path("Directories/NewDirectory2"),
                        id = 2,
                    ),
                ),
            Path("Photos") to
                listOf<NetworkDirectoryDetails>(
                    DefaultNetworkDirectoryDetails(fullPath = Path("Photos/Peeng2.png"), id = 2),
                    DefaultNetworkDirectoryDetails(fullPath = Path("Photos/Jaypeg2.jpg"), id = 3),
                    DefaultNetworkDirectoryDetails(fullPath = Path("Photos/textFile2.txt"), id = 4),
                    DefaultNetworkDirectoryDetails(fullPath = Path("Photos/SubPhotos"), id = 5),
                ),
            Path("Photos/SubPhotos") to
                listOf<NetworkDirectoryDetails>(
                    DefaultNetworkDirectoryDetails(
                        fullPath = Path("Photos/SubPhotos/Peeng3.png"),
                        id = 2,
                    ),
                    DefaultNetworkDirectoryDetails(
                        fullPath = Path("Photos/SubPhotos/Jaypeg3.jpg"),
                        id = 3,
                    ),
                    DefaultNetworkDirectoryDetails(
                        fullPath = Path("Photos/SubPhotos/textFile3.txt"),
                        id = 4,
                    ),
                ),
        )

    private val successImageName: Path = Path("Photos/Success.png")

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
        if (!connected) {
            print("Success Credentials: $successLoginCredentials\n")
            print("Actual Credentials $credentials\n")
        }

        credentials.toString()
        return connected
    }

    override suspend fun disconnect() {
        connected = false
    }

    override suspend fun getDirectoryDetails(path: Path): NetworkDirectoryDetails? {
        networkContents.values.forEach { details ->
            details.find { detail ->
                detail.fullPath == path
            }?.let {
                return it
            }
        }
        return null
    }

    override suspend fun getDirectoryContents(path: Path): List<NetworkDirectoryDetails> {
        print("Getting Directory Contents '$path'\n")

        return networkContents[path] ?: emptyList()
    }

    override suspend fun openDirectory(path: Path): Path? {
        print("Opening Directory ${path}\n")
        if (networkContents.containsKey(path)) {
            return path
        }
        return null
    }

    override suspend fun getSharedImage(path: Path): SharedImage? {
        print("Opening Image ${path}\n")
        if (path == successImageName) {
            throw Exception("Success") // TODO: This is messy, but SharedImageIs expect
        }
        return null
    }

    override suspend fun folderExists(path: Path): Boolean? =
        if (path == Path.EMPTY) {
            null
        } else {
            networkContents.keys.contains(path)
        }

    override suspend fun savePlaylist(
        playlistName: String,
        json: String,
    ): Boolean {
        playlists[playlistName] = json
        return true
    }

    override suspend fun getPlaylists(): List<String> = playlists.values.toList()

    override suspend fun deletePlaylist(playlistName: String) {
        playlists.remove(playlistName)
    }
}
