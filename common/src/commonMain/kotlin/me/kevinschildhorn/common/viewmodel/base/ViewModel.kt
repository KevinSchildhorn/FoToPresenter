package me.kevinschildhorn.common.viewmodel.base

import kotlinx.coroutines.CoroutineScope

expect abstract class ViewModel() {
    val viewModelScope: CoroutineScope
    protected open fun onCleared()
}