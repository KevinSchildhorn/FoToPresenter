package com.kevinschildhorn.fotopresenter.ui.viewmodel

import androidx.compose.ui.graphics.ImageBitmap
import com.kevinschildhorn.fotopresenter.ui.SharedImage
import com.kevinschildhorn.fotopresenter.ui.state.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PhotoDirectoryViewModel(private val image: SharedImage?) : ViewModel() {
    private val _imageState: MutableStateFlow<State<ImageBitmap>> = MutableStateFlow(State.IDLE)
    val imageState: StateFlow<State<ImageBitmap>> = _imageState.asStateFlow()

    fun refreshImageBitmap() {
        var state:State<ImageBitmap> = State.LOADING
        _imageState.update { state }
        viewModelScope.launch(Dispatchers.Default) {
            image?.getImageBitmap()?.let {
                state = State.SUCCESS(it)
                _imageState.update { state }
            } ?: run {
                state = State.ERROR("No Image Found")
                _imageState.update { state }
            }
        }
    }
}

