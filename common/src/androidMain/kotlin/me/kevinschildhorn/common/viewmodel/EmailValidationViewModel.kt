package me.kevinschildhorn.common.viewmodel

import androidx.compose.runtime.*
import kotlinx.coroutines.*
import me.kevinschildhorn.android.viewModel.SharedEmailValidationViewModel

@OptIn(ExperimentalCoroutinesApi::class)
class EmailValidationViewModel : SharedEmailValidationViewModel() {
    var username by mutableStateOf("")
        private set

    fun updateUsername(input: String) {
        username = input
        verifyEmail(input)
    }

    fun focusChanged(isFocused: Boolean) {
        emailTextFieldState.value.focusChanged(isFocused)
    }
}