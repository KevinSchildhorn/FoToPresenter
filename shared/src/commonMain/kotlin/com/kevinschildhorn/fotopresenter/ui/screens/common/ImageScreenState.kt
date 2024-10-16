package com.kevinschildhorn.fotopresenter.ui.screens.common

import com.kevinschildhorn.fotopresenter.data.ImageDirectory
import com.kevinschildhorn.fotopresenter.ui.shared.SharedImage

data class ImageScreenState(
    val imageDirectories: List<ImageDirectory> = emptyList(),
    val selectedImageIndex: Int? = null,
    val selectedImage: SharedImage? = null,
) {
    val selectedImageDirectory: ImageDirectory?
        get() =
            selectedImageIndex?.let {
                imageDirectories.getOrNull(it)
            }
}
