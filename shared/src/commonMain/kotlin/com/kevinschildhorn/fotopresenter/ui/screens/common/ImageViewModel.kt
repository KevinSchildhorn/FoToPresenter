package com.kevinschildhorn.fotopresenter.ui.screens.common

import co.touchlab.kermit.Logger
import com.kevinschildhorn.fotopresenter.UseCaseFactory
import com.kevinschildhorn.fotopresenter.data.ImageDirectory
import com.kevinschildhorn.fotopresenter.extension.getNextIndex
import com.kevinschildhorn.fotopresenter.extension.getPreviousIndex
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

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

    fun cancelImageJobs()
}

class DefaultImageViewModel(private val logger: Logger? = null) : ImageViewModel, KoinComponent {
    @Suppress("ktlint:standard:property-naming")
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
        logger?.i { "Setting selected image to $index" }
        _uiState.update { it.copy(selectedImageIndex = index) }
        updateSelectedImage()
    }

    override fun clearPresentedImage() {
        _uiState.update { it.copy(selectedImage = null, selectedImageIndex = null) }
    }

    override fun cancelImageJobs() {
        logger?.v { "Cancelling Image Jobs" }
        scope?.coroutineContext?.cancelChildren()
    }

    private fun updateSelectedImage() {
        logger?.d { "Updating Selected Index" }
        with(imageUiState.value) {
            selectedImageDirectory?.let {
                logger?.d { "Image Directory found, showing photo" }
                showPhoto(it)
            } ?: run {
                logger?.w { "Image Directory NOT found" }
            }
        }
    }

    private fun showPhoto(imageDirectory: ImageDirectory) {
        logger?.i { "Showing Photo" }
        scope?.launch(Dispatchers.Default) {
            val retrieveImagesUseCase = UseCaseFactory.retrieveImageUseCase
            // logger?.d { "Retrieving Image ${imageDirectory.image}" }

            val imageBitmap = retrieveImagesUseCase(imageDirectory)
            _uiState.update { it.copy(selectedImage = imageBitmap) }
            logger?.d { "Updated selected image ${_uiState.value.selectedImage}" }
        }
    }
}
