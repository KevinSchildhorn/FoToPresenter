package com.kevinschildhorn.fotopresenter.data.network

import com.kevinschildhorn.fotopresenter.data.Path
import com.kevinschildhorn.fotopresenter.data.login.LoginCredentials
import com.kevinschildhorn.fotopresenter.ui.shared.SharedImage

@Suppress("ktlint:standard:max-line-length")
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
                            fullPath = Path("Photos\\Games"),
                            id = 1,
                        ),
                        DefaultNetworkDirectoryDetails(
                            fullPath = Path("Photos\\Foxes"),
                            id = 2,
                        ),

                        DefaultNetworkDirectoryDetails(
                            fullPath =
                                Path(
                                    "https://images.pexels.com/photos/30445480/pexels-photo-30445480.jpeg",
                                ),
                            id = 3,
                        ),
                        DefaultNetworkDirectoryDetails(
                            fullPath =
                                Path(
                                    "https://images.pexels.com/photos/32614661/pexels-photo-32614661.jpeg",
                                ),
                            id = 4,
                        ),
                        DefaultNetworkDirectoryDetails(
                            fullPath =
                                Path(
                                    "https://images.pexels.com/photos/32782803/pexels-photo-32782803.jpeg",
                                ),
                            id = 5,
                        ),
                        DefaultNetworkDirectoryDetails(
                            fullPath =
                                Path(
                                    "https://images.pexels.com/photos/32800596/pexels-photo-32800596.jpeg",
                                ),
                            id = 6,
                        ),
                        DefaultNetworkDirectoryDetails(
                            fullPath =
                                Path(
                                    "https://images.pexels.com/photos/32754882/pexels-photo-32754882.jpeg",
                                ),
                            id = 7,
                        ),
                        DefaultNetworkDirectoryDetails(
                            fullPath =
                                Path(
                                    "https://images.pexels.com/photos/32435046/pexels-photo-32435046.jpeg",
                                ),
                            id = 8,
                        ),
                        DefaultNetworkDirectoryDetails(
                            fullPath =
                                Path(
                                    "https://images.pexels.com/photos/16654239/pexels-photo-16654239.jpeg",
                                ),
                            id = 9,
                        ),
                        DefaultNetworkDirectoryDetails(
                            fullPath =
                                Path(
                                    "https://images.pexels.com/photos/32206160/pexels-photo-32206160.jpeg",
                                ),
                            id = 10,
                        ),
                        DefaultNetworkDirectoryDetails(
                            fullPath =
                                Path(
                                    "https://images.pexels.com/photos/12041931/pexels-photo-12041931.jpeg",
                                ),
                            id = 11,
                        ),
                        DefaultNetworkDirectoryDetails(
                            fullPath =
                                Path(
                                    "https://images.pexels.com/photos/32409117/pexels-photo-32409117.jpeg",
                                ),
                            id = 12,
                        ),
                        DefaultNetworkDirectoryDetails(
                            fullPath =
                                Path(
                                    "https://images.pexels.com/photos/19570518/pexels-photo-19570518.jpeg",
                                ),
                            id = 13,
                        ),
                        DefaultNetworkDirectoryDetails(
                            fullPath =
                                Path(
                                    "https://images.pexels.com/photos/16741732/pexels-photo-16741732.jpeg",
                                ),
                            id = 14,
                        ),
                        DefaultNetworkDirectoryDetails(
                            fullPath =
                                Path(
                                    "https://images.pexels.com/photos/19128693/pexels-photo-19128693.jpeg",
                                ),
                            id = 15,
                        ),
                        DefaultNetworkDirectoryDetails(
                            fullPath =
                                Path(
                                    "https://images.pexels.com/photos/32613124/pexels-photo-32613124.jpeg",
                                ),
                            id = 16,
                        ),
                        DefaultNetworkDirectoryDetails(
                            fullPath =
                                Path(
                                    "https://images.pexels.com/photos/32283936/pexels-photo-32283936.jpeg",
                                ),
                            id = 17,
                        ),
                        DefaultNetworkDirectoryDetails(
                            fullPath =
                                Path(
                                    "https://images.pexels.com/photos/32650223/pexels-photo-32650223.jpeg",
                                ),
                            id = 18,
                        ),
                        DefaultNetworkDirectoryDetails(
                            fullPath =
                                Path(
                                    "https://images.pexels.com/photos/20183314/pexels-photo-20183314.jpeg",
                                ),
                            id = 19,
                        ),
                        DefaultNetworkDirectoryDetails(
                            fullPath =
                                Path(
                                    "https://images.pexels.com/photos/20042965/pexels-photo-20042965.jpeg",
                                ),
                            id = 20,
                        ),
                        DefaultNetworkDirectoryDetails(
                            fullPath =
                                Path(
                                    "https://images.pexels.com/photos/32696032/pexels-photo-32696032.jpeg",
                                ),
                            id = 21,
                        ),
                        DefaultNetworkDirectoryDetails(
                            fullPath =
                                Path(
                                    "https://images.pexels.com/photos/31452622/pexels-photo-31452622.jpeg",
                                ),
                            id = 22,
                        ),
                        DefaultNetworkDirectoryDetails(
                            fullPath =
                                Path(
                                    "https://images.pexels.com/photos/31353383/pexels-photo-31353383.jpeg",
                                ),
                            id = 23,
                        ),
                        DefaultNetworkDirectoryDetails(
                            fullPath =
                                Path(
                                    "https://images.pexels.com/photos/830829/pexels-photo-830829.jpeg",
                                ),
                            id = 24,
                        ),
                        DefaultNetworkDirectoryDetails(
                            fullPath =
                                Path(
                                    "https://images.pexels.com/photos/20409358/pexels-photo-20409358.jpeg",
                                ),
                            id = 25,
                        ),
                        DefaultNetworkDirectoryDetails(
                            fullPath =
                                Path(
                                    "https://images.pexels.com/photos/7007275/pexels-photo-7007275.jpeg",
                                ),
                            id = 26,
                        ),
                        DefaultNetworkDirectoryDetails(
                            fullPath =
                                Path(
                                    "https://images.pexels.com/photos/32718829/pexels-photo-32718829.jpeg",
                                ),
                            id = 27,
                        ),
                        DefaultNetworkDirectoryDetails(
                            fullPath =
                                Path(
                                    "https://images.pexels.com/photos/31487012/pexels-photo-31487012.jpeg",
                                ),
                            id = 28,
                        ),
                        DefaultNetworkDirectoryDetails(
                            fullPath =
                                Path(
                                    "https://images.pexels.com/photos/31690622/pexels-photo-31690622.jpeg",
                                ),
                            id = 29,
                        ),
                        DefaultNetworkDirectoryDetails(
                            fullPath =
                                Path(
                                    "https://images.pexels.com/photos/32495539/pexels-photo-32495539.jpeg",
                                ),
                            id = 30,
                        ),

                        DefaultNetworkDirectoryDetails(
                            fullPath =
                                Path(
                                    "https://images.pexels.com/photos/32748147/pexels-photo-32748147.jpeg",
                                ),
                            id = 31,
                        ),
                        DefaultNetworkDirectoryDetails(
                            fullPath =
                                Path(
                                    "https://img.freepik.com/premium-vector/colorful-bird-with-colorful-bird-its-tail_1023984-27489.jpg",
                                ),
                            id = 32,
                        ),
                        DefaultNetworkDirectoryDetails(
                            fullPath =
                                Path(
                                    "https://img.freepik.com/premium-photo/swan-png-sticker-animal-illustration-mixed-media-transparent-background_53876-1030088.jpg",
                                ),
                            id = 33,
                        ),

                        DefaultNetworkDirectoryDetails(
                            fullPath =
                                Path(
                                    "https://images.pexels.com/photos/31098335/pexels-photo-31098335.jpeg",
                                ),
                            id = 34,
                        ),
                        DefaultNetworkDirectoryDetails(
                            fullPath =
                                Path(
                                    "https://images.pexels.com/photos/31353934/pexels-photo-31353934.jpeg",
                                ),
                            id = 35,
                        ),
                        DefaultNetworkDirectoryDetails(
                            fullPath =
                                Path(
                                    "https://images.pexels.com/photos/32489809/pexels-photo-32489809.jpeg",
                                ),
                            id = 36,
                        ),
                        DefaultNetworkDirectoryDetails(
                            fullPath =
                                Path(
                                    "https://images.pexels.com/photos/31530707/pexels-photo-31530707.jpeg",
                                ),
                            id = 37,
                        ),
                        DefaultNetworkDirectoryDetails(
                            fullPath =
                                Path(
                                    "https://images.pexels.com/photos/32493668/pexels-photo-32493668.jpeg",
                                ),
                            id = 38,
                        ),
                        DefaultNetworkDirectoryDetails(
                            fullPath =
                                Path(
                                    "https://images.pexels.com/photos/31958931/pexels-photo-31958931.jpeg",
                                ),
                            id = 39,
                        ),
                        DefaultNetworkDirectoryDetails(
                            fullPath =
                                Path(
                                    "https://images.pexels.com/photos/32672612/pexels-photo-32672612.jpeg",
                                ),
                            id = 40,
                        ),
                        DefaultNetworkDirectoryDetails(
                            fullPath =
                                Path(
                                    "https://images.pexels.com/photos/6441050/pexels-photo-6441050.jpeg",
                                ),
                            id = 41,
                        ),
                        DefaultNetworkDirectoryDetails(
                            fullPath =
                                Path(
                                    "https://images.pexels.com/photos/31034565/pexels-photo-31034565.jpeg",
                                ),
                            id = 42,
                        ),
                        DefaultNetworkDirectoryDetails(
                            fullPath =
                                Path(
                                    "https://images.pexels.com/photos/31632909/pexels-photo-31632909.jpeg",
                                ),
                            id = 43,
                        ),
                        DefaultNetworkDirectoryDetails(
                            fullPath =
                                Path(
                                    "https://images.pexels.com/photos/31173352/pexels-photo-31173352.jpeg",
                                ),
                            id = 44,
                        ),
                        DefaultNetworkDirectoryDetails(
                            fullPath =
                                Path(
                                    "https://images.pexels.com/photos/32660267/pexels-photo-32660267.jpeg",
                                ),
                            id = 45,
                        ),
                        DefaultNetworkDirectoryDetails(
                            fullPath =
                                Path(
                                    "https://images.pexels.com/photos/32683758/pexels-photo-32683758.jpeg",
                                ),
                            id = 46,
                        ),
                        DefaultNetworkDirectoryDetails(
                            fullPath =
                                Path(
                                    "https://images.pexels.com/photos/13725650/pexels-photo-13725650.jpeg",
                                ),
                            id = 47,
                        ),
                        DefaultNetworkDirectoryDetails(
                            fullPath =
                                Path(
                                    "https://images.pexels.com/photos/32304890/pexels-photo-32304890.jpeg",
                                ),
                            id = 48,
                        ),
                        DefaultNetworkDirectoryDetails(
                            fullPath =
                                Path(
                                    "https://images.pexels.com/photos/24861173/pexels-photo-24861173.jpeg",
                                ),
                            id = 49,
                        ),
                        DefaultNetworkDirectoryDetails(
                            fullPath =
                                Path(
                                    "https://images.pexels.com/photos/29873592/pexels-photo-29873592.jpeg",
                                ),
                            id = 50,
                        ),
                        DefaultNetworkDirectoryDetails(
                            fullPath =
                                Path(
                                    "https://images.pexels.com/photos/32657574/pexels-photo-32657574.jpeg",
                                ),
                            id = 51,
                        ),
                    ),


            Path("Photos\\Games") to
                    listOf<NetworkDirectoryDetails>(
                        DefaultNetworkDirectoryDetails(
                            fullPath = Path("https://cdn.mos.cms.futurecdn.net/SJoqSEKazc32mEmYs9YMGR.jpg"),
                            id = 1,
                        ),
                        DefaultNetworkDirectoryDetails(
                            fullPath = Path("https://a.fsdn.com/sd/firehose/014/345/726-1.png"),
                            id = 2,
                        ),
                        DefaultNetworkDirectoryDetails(
                            fullPath = Path("https://lifehacker.com/imagery/articles/01HF2HJGS6VBM52ER27M036N0A/hero-image.jpg"),
                            id = 3,
                        ),
                        DefaultNetworkDirectoryDetails(
                            fullPath =
                                Path(
                                    "https://static0.gamerantimages.com/wordpress/wp-content/uploads/Fortnite-gameplay-screenshot-1.jpg",
                                ),
                            id = 4,
                        ),
                    ),
            Path("Photos\\Foxes") to
                    listOf<NetworkDirectoryDetails>(
                        DefaultNetworkDirectoryDetails(
                            fullPath = Path("Photos\\Foxes\\Fennec"),
                            id = 1,
                        ),
                        DefaultNetworkDirectoryDetails(
                            fullPath = Path("https://upload.wikimedia.org/wikipedia/commons/3/30/Vulpes_vulpes_ssp_fulvus.jpg"),
                            id = 2,
                        ),
                        DefaultNetworkDirectoryDetails(
                            fullPath = Path("https://i.natgeofe.com/k/6496b566-0510-4e92-84e8-7a0cf04aa505/red-fox-portrait_square.jpg"),
                            id = 3,
                        ),
                    ),
            Path("Photos\\Foxes\\Fennec") to
                    listOf<NetworkDirectoryDetails>(
                        DefaultNetworkDirectoryDetails(
                            fullPath = Path("https://www.awsfzoo.com/media/IMG_0645.jpg"),
                            id = 2,
                        ),
                        DefaultNetworkDirectoryDetails(
                            fullPath =
                                Path(
                                    "https://www.northgeorgiazoo.com/uploads/2/8/9/8/2898594/untitled-design-2022-02-16t103624-545_orig.png",
                                ),
                            id = 3,
                        ),
                    ),
        )

    override val isConnected: Boolean = true

    override suspend fun connect(credentials: LoginCredentials): Boolean = true

    override suspend fun disconnect() {}

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

    override suspend fun getDirectoryContents(path: Path): List<NetworkDirectoryDetails> =
        networkContents[path] ?: emptyList()

    override suspend fun openDirectory(path: Path): Path? {
        if (networkContents.containsKey(path)) {
            return path
        }
        return null
    }

    override suspend fun getSharedImage(path: Path): SharedImage? = SharedImage(ByteArray(0))

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
    ): Boolean = true

    override suspend fun getPlaylists(): List<String> = emptyList()

    override suspend fun deletePlaylist(playlistName: String) {
    }
}
