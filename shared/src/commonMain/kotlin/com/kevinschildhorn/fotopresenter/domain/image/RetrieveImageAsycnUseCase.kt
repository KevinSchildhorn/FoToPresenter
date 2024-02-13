package com.kevinschildhorn.fotopresenter.domain.image

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap
import co.touchlab.kermit.Logger
import com.kevinschildhorn.fotopresenter.data.ImageDirectory
import com.kevinschildhorn.fotopresenter.data.State
import com.kevinschildhorn.fotopresenter.data.datasources.ImageCacheDataSource
import kotlinx.coroutines.delay


/**
Retrieving Image Bitmap
 **/
class RetrieveImageAsyncUseCase(
    private val imageCacheDataSource: ImageCacheDataSource,
    private val logger: Logger,
) {

    suspend operator fun invoke(
        directory: ImageDirectory,
        imageSize: Int,
        callback: (State<ImageBitmap>) -> Unit
    ) {
        val imageName = "\"${directory.details.fullPath}\""
        logger.i { "Starting to get Image $imageName" }

        callback(State.LOADING)
        /*
        imageCacheDataSource.getImage(directory.details)?.let {
            logger.i { "$imageName found in cache, using that" }
            callback(State.SUCCESS(it))
        }*/
/*
        // First Try
        try {
            todo(directory, imageSize, callback)
            return
        } catch (e:Exception) {
            delay(250)
        }
        // Second Try
        try {
            todo(directory, imageSize, callback)
            return
        } catch (e:Exception) {
            delay(500)
        }
        // Third Try
        try {
            todo(directory, imageSize, callback)
            return
        } catch (e:Exception) {
            delay(1000)
        }*/
    }

    private fun todo(
        directory: ImageDirectory,
        imageSize: Int,
        callback: (State<ImageBitmap>) -> Unit
    ) {
        logger.i { "Getting Image Bitmap from File ${directory.name}" }
        val imageBitmap: ImageBitmap? = directory.image?.getImageBitmap(imageSize)
        imageBitmap?.let {
            logger.i { "Caching new Image ${directory.name}" }
            imageCacheDataSource.saveImage(directory.details, it)
            callback(State.SUCCESS(imageBitmap))
        } ?: run {
            callback(State.ERROR("Couldn't Load"))
        }
    }
}