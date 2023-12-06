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
        _imageState.update { State.LOADING }
        viewModelScope.launch(Dispatchers.Default) {
            image?.getImageBitmap()?.let {
                _imageState.update { State.SUCCESS(it) }
            } ?: run {
                _imageState.update { State.ERROR("No Image Found") }
            }
        }
    }
}

