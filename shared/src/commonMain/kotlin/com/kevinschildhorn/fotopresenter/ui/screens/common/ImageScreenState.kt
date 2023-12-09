package com.kevinschildhorn.fotopresenter.ui.screens.common

import androidx.compose.ui.graphics.ImageBitmap
import com.kevinschildhorn.fotopresenter.data.ImageDirectory

data class ImageScreenState(
    val imageDirectories: List<ImageDirectory> = emptyList(),
    val selectedImageIndex: Int? = null,
    val selectedImage: ImageBitmap? = null,
)