package me.kevinschildhorn.common.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import me.kevinschildhorn.android.viewModel.SharedEmailValidationViewModel

@OptIn(ExperimentalCoroutinesApi::class)
class EmailValidationViewModel : ViewModel() {
    var username by mutableStateOf("")
        private set

    val userNameHasError: StateFlow<Boolean> =
        snapshotFlow { username }
            .mapLatest { username.length >= 2 }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = false
            )

    fun updateUsername(input: String) {
        username = input
    }
}