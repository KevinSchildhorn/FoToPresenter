package com.kevinschildhorn.fotopresenter.data.repositories

import co.touchlab.kermit.Logger
import coil3.fetch.FetchResult
import com.kevinschildhorn.fotopresenter.data.datasources.image.CachedImageDataSource
import com.kevinschildhorn.fotopresenter.data.datasources.image.NetworkImageDataSource
import com.kevinschildhorn.fotopresenter.data.network.NetworkDirectoryDetails
import com.kevinschildhorn.fotopresenter.ui.shared.SharedImage

class ImageRepository(
    private val remoteImageDataSource: NetworkImageDataSource,
    private val localImageDataSource: CachedImageDataSource,
    private val logger: Logger?,
) {

    suspend fun getFetchResult(directoryDetails: NetworkDirectoryDetails, size: Int): FetchResult? {
        val image = getImage(directoryDetails, size)
        return image?.getFetchResult(size)
    }

    private suspend fun getImage(directoryDetails: NetworkDirectoryDetails, size: Int): SharedImage? {
        logger?.i { "Getting Image from Cache: ${directoryDetails.name}" }
        val cachedImage = localImageDataSource.getImage(directoryDetails)
        if (cachedImage != null) {
            logger?.i { "Cached image found from Cache: ${directoryDetails.name}" }
            return cachedImage
        }

        logger?.i { "No cached image found, getting image from directory" }
        val image = remoteImageDataSource.getImage(directoryDetails)
        if (image != null) {
            logger?.i { "Storing image in cache: ${directoryDetails.name}" }
            localImageDataSource.saveImage(directoryDetails, image)
        }
        return image
    }
}