package com.kevinschildhorn.fotopresenter.data.network

interface NetworkDirectory {
    val name: String
    val fileExtension: String?
    val id: Int

    val fileName: String
        get() = "${name}.${fileExtension}"

    val isAnImage: Boolean
        get() =
            fileExtension == "png" ||
                    fileExtension == "jpg" ||
                    fileExtension == "jpeg" ||
                    fileExtension == "bmp"

}


class MockNetworkDirectory(
    override val name: String,
    override val fileExtension: String? = null,
    override val id: Int,
) : NetworkDirectory