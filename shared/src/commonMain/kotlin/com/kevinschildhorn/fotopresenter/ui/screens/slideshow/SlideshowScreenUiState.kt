package com.kevinschildhorn.fotopresenter.ui.screens.slideshow

import com.kevinschildhorn.fotopresenter.data.network.NetworkDirectoryDetails

sealed class SlideshowScreenUiState() {
    object Loading : SlideshowScreenUiState()

    class Ready(val selectedImageDirectory: NetworkDirectoryDetails) : SlideshowScreenUiState()
}
