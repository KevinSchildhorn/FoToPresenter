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
        callback: suspend (State<ImageBitmap>) -> Unit,
    ) {
        logger.i { "Starting to get Image ${directory.name}" }
        callback(State.LOADING)

        imageCacheDataSource.getImage(directory.details)?.let {
            logger.i { "Image found in cache, using that" }
            callback(State.SUCCESS(it))
        }

        logger.i { "Getting Image Bitmap from File ${directory.name}" }
        val smallImageBitmap = downloadAndStoreImage(directory, 64)
        callback(smallImageBitmap.asState)

        val largeImageBitmap = downloadAndStoreImage(directory, 256)
        callback(largeImageBitmap.asState)
    }

    private fun downloadAndStoreImage(
        directory: ImageDirectory,
        bitmapSize: Int,
    ): ImageBitmap? {
        logger.i { "Getting Image Bitmap from File ${directory.name}" }
        val imageBitmap: ImageBitmap? = directory.image?.getImageBitmap(bitmapSize)
        imageBitmap?.let {
            logger.i { "Caching new Image ${directory.name}" }
            imageCacheDataSource.saveImage(directory.details, it)
        }
        return imageBitmap
    }

    private val ImageBitmap?.asState: State<ImageBitmap>
        get() = this?.let {
            State.SUCCESS(it)
        } ?: State.ERROR("No Image Found")
}
