package com.kevinschildhorn.fotopresenter.ui.screens.slideshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import com.kevinschildhorn.fotopresenter.UseCaseFactory
import com.kevinschildhorn.fotopresenter.data.ImagePreviewNavigator
import com.kevinschildhorn.fotopresenter.data.ImageSlideshowDetails
import com.kevinschildhorn.fotopresenter.data.PlaylistDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import java.util.Timer
import kotlin.concurrent.fixedRateTimer

class SlideshowViewModel(
    private val imagePreviewNavigator: ImagePreviewNavigator,
    private val logger: Logger,
) : ViewModel(), KoinComponent {
    private val _uiState = MutableStateFlow(SlideshowScreenUiState.Loading())
    val uiState: StateFlow<SlideshowScreenUiState> = _uiState
        .combine(imagePreviewNavigator.imagePreviewState) { uiState, imagePreview ->
            logger.v { "New Image Preview State: $imagePreview" }
            if (imagePreview != null) {
                return@combine SlideshowScreenUiState.Ready(
                    selectedImageDirectory = imagePreview,
                    selectedImageIndex = uiState.selectedImageIndex
                )
            }
            return@combine SlideshowScreenUiState.Loading(selectedImageIndex = uiState.selectedImageIndex)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,//(5_000),
            initialValue = SlideshowScreenUiState.Loading(0),
        )

    private var timer: Timer? = null

    fun setSlideshowFromPlaylist(playlistDetails: PlaylistDetails) {
        logger.i { "Starting playlist $playlistDetails" }
        val useCase = UseCaseFactory.retrieveSlideshowFromPlaylistUseCase
        viewModelScope.launch(Dispatchers.Default) {
            val slideshow = useCase(playlistDetails)
            startSlideshow(slideshow)
        }
    }

    fun startSlideshow(details: ImageSlideshowDetails) {
        imagePreviewNavigator.setFolderContents(details.directories)
        imagePreviewNavigator.setImageIndex(0)
        viewModelScope.launch(Dispatchers.Default) {
            startImageTimer()
        }
    }

    fun skipForward() {
        stopImageTimer()
        imagePreviewNavigator.showNextImage()
        startImageTimer()
    }

    fun skipBackwards() {
        stopImageTimer()
        imagePreviewNavigator.showPreviousImage()
        startImageTimer()
    }

    fun stopSlideshow() {
        stopImageTimer()
        imagePreviewNavigator.setFolderContents(emptyList())
    }

    private fun startImageTimer(seconds: Long = 5L) {
        val time = seconds * 1000
        timer =
            fixedRateTimer(period = time, initialDelay = time) {
                viewModelScope.launch(Dispatchers.Main) {
                    imagePreviewNavigator.showNextImage()
                }
            }
    }

    private fun stopImageTimer() {
        timer?.cancel()
        timer = null
    }
}
