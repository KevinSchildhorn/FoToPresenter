package me.kevinschildhorn.common.viewmodel

import androidx.compose.runtime.*
import kotlinx.coroutines.*
import me.kevinschildhorn.android.viewModel.SharedEmailValidationViewModel

@OptIn(ExperimentalCoroutinesApi::class)
class EmailValidationViewModel : SharedEmailValidationViewModel() {
    var username by mutableStateOf("")
        private set

    init {
        viewModelScope.launch {
            delay(1000)
            validationState.collect {
                print("Hello")
                withContext(Dispatchers.IO) {
                    emailTextFieldState.value.updateWithState(it, username)
                }
            }
        }
    }

    fun updateUsername(input: String) {
        username = input
        verifyEmail(input)
    }
}