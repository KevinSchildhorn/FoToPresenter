package com.kevinschildhorn.fotopresenter.domain

import androidx.compose.ui.graphics.ImageBitmap
import co.touchlab.kermit.Logger
import com.kevinschildhorn.fotopresenter.data.ImageDirectory
import com.kevinschildhorn.fotopresenter.data.State
import com.kevinschildhorn.fotopresenter.ui.shared.SharedCache

/**
Retrieving Directories from Location
 **/
class RetrieveImagesUseCase(
    private val logger: Logger,
) {
    suspend operator fun invoke(
        directory: ImageDirectory,
        callback: suspend (State<ImageBitmap>) -> Unit,
    ) {
        callback(State.LOADING)
        SharedCache.getImage(directory.name)?.let {
            callback(State.SUCCESS(it))
        }
        val imageBitmap: ImageBitmap? = directory.image?.getImageBitmap(400)

        callback(
            imageBitmap?.let {
                State.SUCCESS(it)
            } ?: State.ERROR("No Image Found"),
        )
        imageBitmap?.let {
            SharedCache.cacheImage(directory.name, it)
        }
    }
}
