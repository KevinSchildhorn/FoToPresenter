package com.kevinschildhorn.fotopresenter.data.network

interface NetworkDirectory {
    val fullPath: String
    val id: Int

    val fileName: String
        get() = fullPath.split("\\").last()

    val name: String
        get() = fileName.split(".").first()
    val fileExtension: String?
        get() = fileName.split(".").takeIf { it.count() > 1 }?.last()

    val isDirectory: Boolean
        get() = this.fileExtension.isNullOrEmpty()

    val isAnImage: Boolean
        get() =
            fileExtension == "png" ||
                fileExtension == "jpg" ||
                fileExtension == "jpeg" ||
                fileExtension == "bmp"
}

class MockNetworkDirectory(
    override val fullPath: String,
    override val id: Int,
) : NetworkDirectory
