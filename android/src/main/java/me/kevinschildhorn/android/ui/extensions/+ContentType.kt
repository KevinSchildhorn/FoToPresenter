package me.kevinschildhorn.android.ui.extensions

import androidx.compose.ui.layout.ContentScale
import me.kevinschildhorn.common.ui.style.image.CropStyle


val CropStyle.asContentScale: ContentScale
    get() = when (this) {
        CropStyle.SCALE -> ContentScale.FillBounds
        CropStyle.FILL -> ContentScale.Crop
        CropStyle.FIT -> ContentScale.Fit
    }