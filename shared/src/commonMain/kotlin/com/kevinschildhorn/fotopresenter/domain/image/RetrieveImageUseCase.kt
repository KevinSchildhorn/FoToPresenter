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

    private val IMAGE_SIZE_SMALL = 64
    private val IMAGE_SIZE_MEDIUM = 256
    private val IMAGE_SIZE_LARGE = 512
    private val IMAGE_SIZE_EXTRA_LARGE = 1024

    suspend operator fun invoke(
        directory: ImageDirectory,
        callback: suspend (State<ImageBitmap>) -> Unit,
    ) {
        val imageName = "\"${directory.details.fullPath}\""
        logger.i { "Starting to get Image $imageName" }
        callback(State.LOADING)

        var workingWidth: Int = 0
        var updatedImage: Boolean = false

        imageCacheDataSource.getImage(directory.details)?.let {
            logger.i { "$imageName found in cache, using that" }
            callback(State.SUCCESS(it))
            workingWidth = it.width
        }
        logger.i { "Getting Image Bitmap from File $imageName" }
        if (workingWidth <= IMAGE_SIZE_SMALL) {
            logger.i { "Getting Small Bitmap for $imageName" }

            val smallImageBitmap = downloadAndStoreImage(directory, IMAGE_SIZE_SMALL)
            callback(smallImageBitmap.asState)
            workingWidth = IMAGE_SIZE_SMALL
            updatedImage = true
        }
        if (workingWidth <= IMAGE_SIZE_MEDIUM) {
            logger.i { "Getting Medium Image" }
            val mediumImageBitmap = downloadAndStoreImage(directory, IMAGE_SIZE_MEDIUM)
            callback(mediumImageBitmap.asState)
            workingWidth = IMAGE_SIZE_MEDIUM
            updatedImage = true
        }
        if (workingWidth <= IMAGE_SIZE_LARGE) {
            logger.i { "Getting Large Image" }
            val mediumImageBitmap = downloadAndStoreImage(directory, IMAGE_SIZE_LARGE)
            callback(mediumImageBitmap.asState)
            workingWidth = IMAGE_SIZE_LARGE
            updatedImage = true
        }
        if (workingWidth <= IMAGE_SIZE_EXTRA_LARGE || !updatedImage) {
            logger.i { "Getting Extra Large Image" }
            val mediumImageBitmap = downloadAndStoreImage(directory, IMAGE_SIZE_EXTRA_LARGE)
            callback(mediumImageBitmap.asState)
            workingWidth = IMAGE_SIZE_EXTRA_LARGE
        }
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
