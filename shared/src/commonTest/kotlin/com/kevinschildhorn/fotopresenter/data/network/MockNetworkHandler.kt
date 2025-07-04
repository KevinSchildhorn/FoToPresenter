package com.kevinschildhorn.fotopresenter.data.network

import com.kevinschildhorn.fotopresenter.data.Path
import com.kevinschildhorn.fotopresenter.data.login.LoginCredentials
import com.kevinschildhorn.fotopresenter.ui.shared.SharedImage
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import java.time.Month

object MockNetworkHandler : NetworkHandler {
    private val successLoginCredentials =
        LoginCredentials(
            "192.168.1.1",
            "admin",
            "password",
            "Public",
            shouldAutoConnect = true,
        )

    private const val PHOTO_DIRECTORY_ID: Long = 5L

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
                    DefaultNetworkDirectoryDetails(
                        fullPath = Path("Photos"),
                        id = PHOTO_DIRECTORY_ID,
                        dateMillis = getMillis(2024, Month.MAY, dayOfMonth = 1),
                    ),
                    DefaultNetworkDirectoryDetails(
                        fullPath = Path("NewDirectory"),
                        id = 1,
                        dateMillis = getMillis(2024, Month.MAY, dayOfMonth = 23),
                    ),
                    DefaultNetworkDirectoryDetails(
                        fullPath = Path("Peeng.png"),
                        id = 75,
                        dateMillis = getMillis(2024, Month.MAY, dayOfMonth = 20),
                    ),
                    DefaultNetworkDirectoryDetails(
                        fullPath = Path("Jaypeg.jpg"),
                        id = 3,
                        dateMillis = getMillis(2024, Month.MAY, dayOfMonth = 10),
                    ),
                    DefaultNetworkDirectoryDetails(
                        fullPath = Path("textFile.txt"),
                        id = 4,
                        dateMillis = getMillis(2024, Month.MAY, dayOfMonth = 1),
                    ),
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
                    DefaultNetworkDirectoryDetails(fullPath = Path("Peeng2.png"), id = 2),
                    DefaultNetworkDirectoryDetails(fullPath = Path("Jaypeg2.jpg"), id = 3),
                    DefaultNetworkDirectoryDetails(fullPath = Path("textFile2.txt"), id = 4),
                    DefaultNetworkDirectoryDetails(fullPath = Path("SubPhotos"), id = 5),
                ),
            Path("Photos\\SubPhotos") to
                listOf<NetworkDirectoryDetails>(
                    DefaultNetworkDirectoryDetails(fullPath = Path("SubSubPhotos"), id = 1),
                    DefaultNetworkDirectoryDetails(fullPath = Path("Peeng3.png"), id = 2),
                    DefaultNetworkDirectoryDetails(fullPath = Path("Jaypeg3.jpg"), id = 3),
                    DefaultNetworkDirectoryDetails(fullPath = Path("textFile3.txt"), id = 4),
                ),
            Path("Photos\\SubPhotos\\SubSubPhotos") to
                listOf<NetworkDirectoryDetails>(
                    DefaultNetworkDirectoryDetails(fullPath = Path("Peeng4.png"), id = 2),
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
        return connected
    }

    override suspend fun disconnect() {
        connected = false
    }

    override suspend fun getDirectoryDetails(path: Path): NetworkDirectoryDetails? {
        networkContents.values.forEach { details ->
            details
                .find { detail ->
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

    override suspend fun setSharedImage(
        path: Path,
        sharedImage: SharedImage,
    ): Boolean = true

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

    private fun getMillis(
        year: Int,
        month: Month,
        dayOfMonth: Int = 1,
    ) = LocalDateTime(year = year, month = month, dayOfMonth = dayOfMonth, 12, 0, 0)
        .toInstant(TimeZone.UTC)
        .toEpochMilliseconds()
}
