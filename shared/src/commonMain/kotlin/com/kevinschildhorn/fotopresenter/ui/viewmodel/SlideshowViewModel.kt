package com.kevinschildhorn.fotopresenter.ui.viewmodel

import co.touchlab.kermit.Logger
import com.kevinschildhorn.fotopresenter.data.ImageSlideshowDetails
import org.koin.core.component.KoinComponent

class SlideshowViewModel(
    private val logger: Logger,
) : ViewModel(), KoinComponent {

    fun setSlideshow(details: ImageSlideshowDetails) {

    }
}