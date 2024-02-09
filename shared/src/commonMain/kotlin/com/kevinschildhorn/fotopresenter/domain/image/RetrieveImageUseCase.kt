package com.kevinschildhorn.fotopresenter.domain.image

import androidx.compose.ui.graphics.ImageBitmap
import co.touchlab.kermit.Logger
import com.kevinschildhorn.fotopresenter.data.ImageDirectory
import com.kevinschildhorn.fotopresenter.data.State
import com.kevinschildhorn.fotopresenter.data.datasources.ImageCacheDataSource

/**
Retrieving Image Bitmap
 **/
class RetrieveImageUseCase(
    private val imageCacheDataSource: ImageCacheDataSource,
    private val logger: Logger,
) {

    suspend operator fun invoke(
        directory: ImageDirectory,
        imageSize: Int,
    ): ImageBitmap? {
        val imageName = "\"${directory.details.fullPath}\""
        logger.i { "Starting to get Image $imageName" }

        imageCacheDataSource.getImage(directory.details)?.let {
            logger.i { "$imageName found in cache, using that" }
            return it
        }

        logger.i { "Getting Image Bitmap from File ${directory.name}" }
        val imageBitmap: ImageBitmap? = directory.image?.getImageBitmap(imageSize)
        imageBitmap?.let {
            logger.i { "Caching new Image ${directory.name}" }
            imageCacheDataSource.saveImage(directory.details, it)
        }
        return imageBitmap
    }
}
