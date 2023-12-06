package com.kevinschildhorn.fotopresenter.ui.viewmodel

import androidx.compose.ui.graphics.ImageBitmap
import com.kevinschildhorn.fotopresenter.ui.SharedImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PhotoDirectoryViewModel(private val image: SharedImage?) : ViewModel() {

    private val _imageState:MutableStateFlow<ImageBitmap?> = MutableStateFlow(null)
    val imageState: StateFlow<ImageBitmap?> = _imageState.asStateFlow()

    fun refreshImageBitmap(){
        viewModelScope.launch(Dispatchers.Default) {
            _imageState.value = image?.getImageBitmap()
        }
    }
}