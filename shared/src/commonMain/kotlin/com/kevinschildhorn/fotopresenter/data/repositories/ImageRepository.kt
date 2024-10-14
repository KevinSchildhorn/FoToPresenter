package com.kevinschildhorn.fotopresenter.data.repositories

import co.touchlab.kermit.Logger
import coil3.Image
import com.kevinschildhorn.fotopresenter.data.datasources.image.CachedImageDataSource
import com.kevinschildhorn.fotopresenter.data.datasources.image.NetworkImageDataSource
import com.kevinschildhorn.fotopresenter.data.network.NetworkDirectoryDetails

class ImageRepository(
    private val remoteImageDataSource: NetworkImageDataSource,
    private val localImageDataSource: CachedImageDataSource,
    private val logger: Logger?,
) {

    suspend fun getCoilImage(directoryDetails: NetworkDirectoryDetails, size: Int): Image? {
        logger?.i { "Getting Image from Cache" }
        val cachedImage = localImageDataSource.getImage(directoryDetails)
        if (cachedImage != null) return cachedImage.getCoilImage(size)

        logger?.i { "No cached image found, getting image from directory" }
        val image = remoteImageDataSource.getImage(directoryDetails)
        if(image != null) {
            logger?.i { "Storing image in cache" }
            localImageDataSource.saveImage(directoryDetails, image)
        }
        return image?.getCoilImage(size)
    }
}