package com.kevinschildhorn.fotopresenter.ui.shared

import kotlinx.coroutines.CoroutineScope

expect abstract class ViewModel() {
    val viewModelScope: CoroutineScope

    protected open fun onCleared()
}