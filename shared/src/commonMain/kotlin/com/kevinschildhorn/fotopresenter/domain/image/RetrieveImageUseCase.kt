package com.kevinschildhorn.fotopresenter.domain.image

import co.touchlab.kermit.Logger
import com.kevinschildhorn.fotopresenter.data.ImageDirectory
import com.kevinschildhorn.fotopresenter.data.datasources.image.CachedImageDataSource
import com.kevinschildhorn.fotopresenter.ui.shared.SharedImage

/**
Retrieving Image Bitmap
 **/
class RetrieveImageUseCase(
    private val cachedImageDataSource: CachedImageDataSource,
    private val logger: Logger,
) {
    suspend operator fun invoke(
        directory: ImageDirectory,
        imageSize: Int,
    ): SharedImage? {
        val imageName = "\"${directory.details.fullPath}\""
        logger.i { "Starting to get Image $imageName" }

        cachedImageDataSource.getImage(directory.details)?.let {
            logger.i { "$imageName found in cache, using that" }
            return it
        }

        logger.i { "Getting Image Bitmap from File ${directory.name}" }
        val imageBitmap: SharedImage? = directory.image
        imageBitmap?.let {
            logger.i { "Caching new Image ${directory.name}" }
            cachedImageDataSource.saveImage(directory.details, it)
        }
        return imageBitmap
    }
}
