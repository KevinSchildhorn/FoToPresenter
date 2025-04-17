package com.kevinschildhorn.fotopresenter.data.network

import com.kevinschildhorn.fotopresenter.BuildKonfig
import com.kevinschildhorn.fotopresenter.data.Path
import com.kevinschildhorn.fotopresenter.data.supportedImageTypes
import kotlinx.datetime.Clock

interface NetworkDirectoryDetails {
    val fullPath: Path
    val dateMillis: Long
    val id: Long

    val fileName: String
        get() = fullPath.fileName

    val name: String
        get() = fileName.split(".").first()
    val fileExtension: String?
        get() = fileName.split(".").takeIf { it.count() > 1 }?.last()

    val isDirectory: Boolean
        get() = this.fileExtension.isNullOrEmpty()

    val isAnImage: Boolean
        get() = supportedImageTypes.contains(fileExtension)

    val model: Any
        get() = if (BuildKonfig.USE_HTTP_IMAGES) fullPath.fileName else this
}

class DefaultNetworkDirectoryDetails(
    override val fullPath: Path,
    override val id: Long,
    override val dateMillis: Long = Clock.System.now().toEpochMilliseconds(),
) : NetworkDirectoryDetails

class MockNetworkDirectoryDetails(
    override val fullPath: Path = Path.EMPTY,
    override val id: Long = 0,
    override val dateMillis: Long = Clock.System.now().toEpochMilliseconds(),
) : NetworkDirectoryDetails
