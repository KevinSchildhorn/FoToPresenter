package com.kevinschildhorn.fotopresenter.ui.screens.slideshow

import co.touchlab.kermit.Logger
import com.kevinschildhorn.fotopresenter.data.ImageSlideshowDetails
import com.kevinschildhorn.fotopresenter.ui.screens.common.DefaultImageViewModel
import com.kevinschildhorn.fotopresenter.ui.screens.common.ImageViewModel
import com.kevinschildhorn.fotopresenter.ui.shared.ViewModel
import com.kevinschildhorn.fotopresenter.ui.screens.directory.DirectoryScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.koin.core.component.KoinComponent

class SlideshowViewModel(
    private val logger: Logger,
) : ViewModel(),
    ImageViewModel by DefaultImageViewModel(),
    KoinComponent {

    private val _uiState = MutableStateFlow(SlideshowScreenState())
    val uiState: StateFlow<SlideshowScreenState> = _uiState.asStateFlow()

    fun setSlideshow(details: ImageSlideshowDetails) {
        _uiState.update { it.copy(slideshowDetails = details) }
        setImageDirectories(details.directories)
    }

}