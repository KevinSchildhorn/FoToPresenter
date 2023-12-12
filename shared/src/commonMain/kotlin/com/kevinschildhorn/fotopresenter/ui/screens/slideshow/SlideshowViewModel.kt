package com.kevinschildhorn.fotopresenter.ui.screens.slideshow

import co.touchlab.kermit.Logger
import com.kevinschildhorn.fotopresenter.data.ImageSlideshowDetails
import com.kevinschildhorn.fotopresenter.ui.screens.common.DefaultImageViewModel
import com.kevinschildhorn.fotopresenter.ui.screens.common.ImageViewModel
import com.kevinschildhorn.fotopresenter.ui.shared.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import java.util.*
import kotlin.concurrent.fixedRateTimer
import kotlin.coroutines.EmptyCoroutineContext

class SlideshowViewModel(
    private val logger: Logger,
) : ViewModel(),
    ImageViewModel by DefaultImageViewModel(logger),
    KoinComponent {

    private val _uiState = MutableStateFlow(SlideshowScreenState())
    val uiState: StateFlow<SlideshowScreenState> = _uiState.asStateFlow()
    private var timer: Timer? = null
    var firstImageTimer: Job? = null

    init {
        setImageScope(viewModelScope)
    }

    fun setSlideshow(details: ImageSlideshowDetails) {
        _uiState.update { it.copy(slideshowDetails = details) }
        setImageDirectories(details.directories)
        setSelectedImage(0)
        viewModelScope.launch(Dispatchers.Default) {
            while(imageUiState.value.selectedImage == null) {
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

    private fun startImageTimer(seconds: Long = 5L) {
        val time = seconds * 1000
        timer = fixedRateTimer(period = time, initialDelay = time) {
            showNextImage()
        }
    }

    private fun stopImageTimer() {
        timer?.cancel()
        timer = null
    }
}
