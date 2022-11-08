package me.kevinschildhorn.common.layers.ui.viewmodel.base

import kotlinx.coroutines.CoroutineScope

expect abstract class ViewModel() {
    val viewModelScope: CoroutineScope
    protected open fun onCleared()
}