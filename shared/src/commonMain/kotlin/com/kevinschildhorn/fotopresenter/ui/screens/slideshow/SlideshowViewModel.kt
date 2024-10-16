package com.kevinschildhorn.fotopresenter.ui.screens.slideshow

import co.touchlab.kermit.Logger
import com.kevinschildhorn.fotopresenter.UseCaseFactory
import com.kevinschildhorn.fotopresenter.data.ImageSlideshowDetails
import com.kevinschildhorn.fotopresenter.data.PlaylistDetails
import com.kevinschildhorn.fotopresenter.ui.screens.common.DefaultImageViewModel
import com.kevinschildhorn.fotopresenter.ui.screens.common.ImageViewModel
import com.kevinschildhorn.fotopresenter.ui.shared.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import java.util.Timer
import kotlin.concurrent.fixedRateTimer

class SlideshowViewModel(
    private val logger: Logger,
) : ViewModel(),
    ImageViewModel by DefaultImageViewModel(logger),
    KoinComponent {
    private val _uiState = MutableStateFlow(SlideshowScreenState())
    val uiState: StateFlow<SlideshowScreenState> = _uiState.asStateFlow()
    private var timer: Timer? = null

    init {
        setImageScope(viewModelScope)
    }

    fun setSlideshowFromPlaylist(playlistDetails: PlaylistDetails) {
        logger.i { "Starting playlist $playlistDetails" }
        val useCase = UseCaseFactory.retrieveSlideshowFromPlaylistUseCase
        viewModelScope.launch(Dispatchers.Default) {
            val slideshow = useCase(playlistDetails)
            setSlideshow(slideshow)
        }
    }

    fun setSlideshow(details: ImageSlideshowDetails) {
        _uiState.update { it.copy(slideshowDetails = details) }
        setImageDirectories(details.directories)
        setSelectedImage(0)
        viewModelScope.launch(Dispatchers.Default) {
            while (imageUiState.value.selectedImage == null) {
                delay(250)
            }
            startImageTimer()
        }
    }

    fun skipForward() {
        stopImageTimer()
        showNextImage()
        startImageTimer()
    }

    fun skipBackwards() {
        stopImageTimer()
        showPreviousImage()
        startImageTimer()
    }

    fun stopSlideshow() {
        stopImageTimer()
        clearPresentedImage()
        setImageDirectories(emptyList())
        _uiState.update { it.copy(slideshowDetails = ImageSlideshowDetails()) }
    }

    private fun startImageTimer(seconds: Long = 5L) {
        val time = seconds * 1000
        timer =
            fixedRateTimer(period = time, initialDelay = time) {
                showNextImage()
            }
    }

    private fun stopImageTimer() {
        timer?.cancel()
        timer = null
    }
}
