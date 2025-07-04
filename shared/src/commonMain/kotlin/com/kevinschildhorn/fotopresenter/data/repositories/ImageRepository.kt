package com.kevinschildhorn.fotopresenter.data.repositories

import co.touchlab.kermit.Logger
import coil3.fetch.FetchResult
import com.kevinschildhorn.fotopresenter.data.datasources.image.NetworkImageDataSource
import com.kevinschildhorn.fotopresenter.data.network.NetworkDirectoryDetails
import com.kevinschildhorn.fotopresenter.ui.shared.SharedImage

class ImageRepository(
    private val remoteImageDataSource: NetworkImageDataSource,
    // private val localImageDataSource: CachedImageDataSource,
    private val logger: Logger?,
) {
    suspend fun getFetchResult(
        directoryDetails: NetworkDirectoryDetails,
        size: Int,
    ): FetchResult? {
        val image = getImage(directoryDetails)
        if (image == null) logger?.e { "Shared Image was not retrieved from directory :${directoryDetails.fullPath}" }
        if (image == null) {
            println("")
        }
        return image?.getFetchResult(logger)
    }

    private suspend fun getImage(directoryDetails: NetworkDirectoryDetails): SharedImage? {
        logger?.d { "Getting Image: ${directoryDetails.name}" }
        logger?.v { "No cached image found, getting image from directory" }
        val image = remoteImageDataSource.getImage(directoryDetails)
        if (image != null) {
            logger?.v { "Storing image in cache: ${directoryDetails.name}" }
        }
        logger?.d { "Image retrieved: ${directoryDetails.name}" }
        return image
    }
}
