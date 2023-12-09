package com.kevinschildhorn.fotopresenter.ui.screens.slideshow

import androidx.compose.ui.graphics.ImageBitmap
import com.kevinschildhorn.fotopresenter.data.ImageSlideshowDetails
import com.kevinschildhorn.fotopresenter.ui.screens.common.ImageScreenState

data class SlideshowScreenState(
    val imageState: ImageScreenState = ImageScreenState(),
    val slideshowDetails: ImageSlideshowDetails = ImageSlideshowDetails(),
)