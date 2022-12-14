package me.kevinschildhorn.common.architecture.ui.viewmodel.base

import kotlinx.coroutines.CoroutineScope

/**
Shared ViewModel class for iOS and Android
 **/
expect abstract class ViewModel() {
    val viewModelScope: CoroutineScope
    protected open fun onCleared()
}
