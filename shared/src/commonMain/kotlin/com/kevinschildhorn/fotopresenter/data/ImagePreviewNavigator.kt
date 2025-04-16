package com.kevinschildhorn.fotopresenter.data

import co.touchlab.kermit.Logger
import com.kevinschildhorn.fotopresenter.data.network.NetworkDirectoryDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/*
 * Handles Navigating a list of Images from start to finish,
 * and emits a flow of the current Images Directory Details.
 */
class ImagePreviewNavigator(
    private val logger: Logger,
) {
    private var directories: List<ImageDirectory> = emptyList()
    private var index: Int = 0

    val _imagePreviewState = MutableStateFlow<NetworkDirectoryDetails?>(null)
    val imagePreviewState: StateFlow<NetworkDirectoryDetails?> = _imagePreviewState.asStateFlow()

    fun setFolderContents(directories: List<ImageDirectory>) {
        logger.i { "Setting Directory Contents" }
        this.directories = directories
        index = -1
        updatePreviewData()
    }

    fun setImageIndex(index: Int?) {
        logger.i { "Set Image Index: $index" }
        this.index = index ?: -1
        updatePreviewData()
    }

    fun showPreviousImage() {
        index -= 1
        if (index < 0) index = directories.count() - 1
        logger.i { "Show previous image at index: $index of ${directories.count()}" }
        updatePreviewData()
    }

    fun showNextImage() {
        index += 1
        if (index >= directories.count()) index = 0
        logger.i { "Show next image at index: $index of ${directories.count()}" }
        updatePreviewData()
    }

    private fun updatePreviewData() {
        val details = directories.getOrNull(index)?.details
        logger.d { "Updating Preview with details at index: $index of count ${directories.count()} : $details" }
        _imagePreviewState.update { details }
    }
}
