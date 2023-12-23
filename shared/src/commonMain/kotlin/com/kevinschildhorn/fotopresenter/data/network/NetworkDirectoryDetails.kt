package com.kevinschildhorn.fotopresenter.data.network

import com.kevinschildhorn.fotopresenter.data.supportedImageTypes
import kotlinx.datetime.Clock

interface NetworkDirectoryDetails {
    val fullPath: String
    val dateMillis:Long
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
        get() = supportedImageTypes.contains(fileExtension)
}

class DefaultNetworkDirectoryDetails(
    override val fullPath: String,
    override val id: Int,
    override val dateMillis: Long = Clock.System.now().toEpochMilliseconds()
) : NetworkDirectoryDetails
