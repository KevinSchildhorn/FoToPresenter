package com.kevinschildhorn.fotopresenter.ui.screens.slideshow

import com.kevinschildhorn.fotopresenter.data.network.NetworkDirectoryDetails

sealed class SlideshowScreenUiState(val selectedImageIndex: Int) {
    class Loading(selectedImageIndex: Int = 0) : SlideshowScreenUiState(selectedImageIndex)

    class Ready(val selectedImageDirectory: NetworkDirectoryDetails, selectedImageIndex: Int = 0) :
        SlideshowScreenUiState(selectedImageIndex)
}
