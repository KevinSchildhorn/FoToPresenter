package com.kevinschildhorn.fotopresenter.data.network

import com.kevinschildhorn.fotopresenter.data.Path
import com.kevinschildhorn.fotopresenter.data.login.LoginCredentials
import com.kevinschildhorn.fotopresenter.ui.shared.SharedImage

object TestingHTTPHandler : NetworkHandler {

    private val networkContents =
        mapOf(
            Path.EMPTY to
                    listOf<NetworkDirectoryDetails>(
                        DefaultNetworkDirectoryDetails(
                            fullPath = Path("Photos"),
                            id = 1,
                        ),
                        DefaultNetworkDirectoryDetails(
                            fullPath = Path("https://img.freepik.com/premium-photo/congo-grey-parrot-bird-illustration-png_53876-1007492.jpg"),
                            id = 75,
                        ),
                    ),

            Path("Photos") to
                    listOf<NetworkDirectoryDetails>(
                        DefaultNetworkDirectoryDetails(
                            fullPath = Path("Games"),
                            id = 1,
                        ),
                        DefaultNetworkDirectoryDetails(
                            fullPath = Path("Foxes"),
                            id = 2,
                        ),
                        DefaultNetworkDirectoryDetails(
                            fullPath = Path("https://img.freepik.com/premium-psd/realistic-beauty-butterfly-isolated-transparent-background_720969-566.jpg"),
                            id = 75,
                        ),
                        DefaultNetworkDirectoryDetails(
                            fullPath = Path("https://img.freepik.com/premium-vector/colorful-bird-with-colorful-bird-its-tail_1023984-27489.jpg"),
                            id = 3,
                        ),
                        DefaultNetworkDirectoryDetails(
                            fullPath = Path("https://img.freepik.com/premium-photo/swan-png-sticker-animal-illustration-mixed-media-transparent-background_53876-1030088.jpg"),
                            id = 4,
                        )
                    ),
            Path("Photos\\Games") to
                    listOf<NetworkDirectoryDetails>(
                        DefaultNetworkDirectoryDetails(fullPath = Path("https://cdn.mos.cms.futurecdn.net/SJoqSEKazc32mEmYs9YMGR.jpg"), id = 1),
                        DefaultNetworkDirectoryDetails(fullPath = Path("https://a.fsdn.com/sd/firehose/014/345/726-1.png"), id = 2),
                        DefaultNetworkDirectoryDetails(fullPath = Path("https://lifehacker.com/imagery/articles/01HF2HJGS6VBM52ER27M036N0A/hero-image.jpg"), id = 3),
                        DefaultNetworkDirectoryDetails(fullPath = Path("https://static0.gamerantimages.com/wordpress/wp-content/uploads/Fortnite-gameplay-screenshot-1.jpg"), id = 4),
                    ),
            Path("Photos\\Foxes") to
                    listOf<NetworkDirectoryDetails>(
                        DefaultNetworkDirectoryDetails(
                            fullPath = Path("Fennec"),
                            id = 1,
                        ),
                        DefaultNetworkDirectoryDetails(fullPath = Path("https://upload.wikimedia.org/wikipedia/commons/3/30/Vulpes_vulpes_ssp_fulvus.jpg"), id = 2),
                        DefaultNetworkDirectoryDetails(fullPath = Path("https://i.natgeofe.com/k/6496b566-0510-4e92-84e8-7a0cf04aa505/red-fox-portrait_square.jpg"), id = 3),
                    ),
            Path("Photos\\Foxes\\Fennec") to
                    listOf<NetworkDirectoryDetails>(
                        DefaultNetworkDirectoryDetails(fullPath = Path("https://www.awsfzoo.com/media/IMG_0645.jpg"), id = 2),
                        DefaultNetworkDirectoryDetails(fullPath = Path("https://www.northgeorgiazoo.com/uploads/2/8/9/8/2898594/untitled-design-2022-02-16t103624-545_orig.png"), id = 3),
                    ),
        )

    override val isConnected: Boolean = true

    override suspend fun connect(credentials: LoginCredentials): Boolean {
        return true
    }

    override suspend fun disconnect() {}

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

    override suspend fun getDirectoryContents(path: Path): List<NetworkDirectoryDetails> =
        networkContents[path] ?: emptyList()


    override suspend fun openDirectory(path: Path): Path? {
        if (networkContents.containsKey(path)) {
            return path
        }
        return null
    }

    override suspend fun getSharedImage(path: Path): SharedImage? =
        SharedImage(ByteArray(0))


    override suspend fun setSharedImage(
        path: Path,
        sharedImage: SharedImage
    ): Boolean {
        return true
    }

    override suspend fun folderExists(path: Path): Boolean? =
        if (path == Path.EMPTY) {
            null
        } else {
            networkContents.keys.contains(path)
        }

    override suspend fun savePlaylist(playlistName: String, json: String): Boolean {
        return true
    }

    override suspend fun getPlaylists(): List<String> {
        return emptyList()
    }

    override suspend fun deletePlaylist(playlistName: String) {
    }

}