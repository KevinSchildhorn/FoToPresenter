package com.kevinschildhorn.fotopresenter.ui.screens.common

import androidx.compose.ui.graphics.ImageBitmap
import co.touchlab.kermit.Logger
import com.kevinschildhorn.fotopresenter.data.ImageDirectory
import com.kevinschildhorn.fotopresenter.data.State
import com.kevinschildhorn.fotopresenter.domain.image.RetrieveImageUseCase
import com.kevinschildhorn.fotopresenter.extension.getNextIndex
import com.kevinschildhorn.fotopresenter.extension.getPreviousIndex
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface ImageViewModel {
    var scope: CoroutineScope?
    val imageUiState: StateFlow<ImageScreenState>

    fun setImageDirectories(directories: List<ImageDirectory>)

    fun showPreviousImage()

    fun showNextImage()

    fun setSelectedImage(index: Int?)

    fun setImageScope(coroutineScope: CoroutineScope) {
        scope = coroutineScope
    }

    fun clearPresentedImage()
}

class DefaultImageViewModel(private val logger: Logger? = null) : ImageViewModel, KoinComponent {
    private val _uiState = MutableStateFlow(ImageScreenState())
    override var scope: CoroutineScope? = null
    override val imageUiState: StateFlow<ImageScreenState> = _uiState.asStateFlow()

    override fun setImageDirectories(directories: List<ImageDirectory>) {
        _uiState.update { it.copy(imageDirectories = directories) }
    }

    override fun showPreviousImage() {
        logger?.i { "Showing Previous Image" }
        with(imageUiState.value) {
            selectedImageIndex?.let { index ->
                logger?.d { "Selected Image Index found. Getting previous index" }
                val nextIndex = imageDirectories.getPreviousIndex(index)
                _uiState.update { it.copy(selectedImageIndex = nextIndex) }
            }
        }
        updateSelectedImage()
    }

    override fun showNextImage() {
        logger?.i { "Showing Next Image" }
        with(imageUiState.value) {
            selectedImageIndex?.let { index ->
                logger?.d { "Selected Image Index found. Getting next index" }
                val nextIndex = imageDirectories.getNextIndex(index)
                _uiState.update { it.copy(selectedImageIndex = nextIndex) }
            }
        }
        updateSelectedImage()
    }

    override fun setSelectedImage(index: Int?) {
        _uiState.update { it.copy(selectedImageIndex = index) }
        updateSelectedImage()
    }

    override fun clearPresentedImage() {
        _uiState.update { it.copy(selectedImage = null, selectedImageIndex = null) }
    }

    private fun updateSelectedImage() {
        logger?.i { "Updating Selected Index" }

        with(imageUiState.value) {
            selectedImageIndex?.let { index ->
                logger?.d { "Selected Image Index found. getting Image Directory" }
                this.imageDirectories.getOrNull(index)?.let {
                    logger?.d { "Image Directory found, showing photo" }
                    showPhoto(it)
                } ?: run {
                    logger?.w { "Image Directory NOT found for index: $index in count ${imageDirectories.count()}" }
                }
            }
        }
    }

    private fun showPhoto(imageDirectory: ImageDirectory) {
        logger?.i { "Showing Photo" }
        scope?.launch(Dispatchers.Default) {
            val retrieveImagesUseCase: RetrieveImageUseCase by inject()
            logger?.d { "Retrieving Image" }
            retrieveImagesUseCase(imageDirectory) { newState: State<ImageBitmap> ->
                logger?.d { "Image State Updated $newState" }
                newState.value?.let { imageBitmap ->
                    _uiState.update { it.copy(selectedImage = imageBitmap) }
                }
            }
        }
    }
}
