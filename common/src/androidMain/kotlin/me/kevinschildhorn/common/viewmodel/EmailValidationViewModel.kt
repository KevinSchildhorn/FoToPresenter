package me.kevinschildhorn.common.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import me.kevinschildhorn.android.viewModel.SharedEmailValidationViewModel
import me.kevinschildhorn.common.ui.ColorOption
import me.kevinschildhorn.common.uilogic.TextFieldState

@OptIn(ExperimentalCoroutinesApi::class)
class EmailValidationViewModel : SharedEmailValidationViewModel() {
    var username by mutableStateOf("")
        private set

    val textFieldState: StateFlow<TextFieldState> = MutableStateFlow(
        TextFieldState(
            hint = "Email",
            defaultColor = ColorOption.NORMAL
        )
    ).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = TextFieldState(
            hint = "Email",
            defaultColor = ColorOption.NORMAL
        )
    )

    val userNameHasError: StateFlow<Boolean> =
        snapshotFlow { username }
            .mapLatest { username.length >= 2 }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = false
            )

    init {
        viewModelScope.launch {
            delay(1000)
            validationState.collect {
                print("Hello")
                withContext(Dispatchers.IO) {
                    textFieldState.value.updateWithState(it, username)
                }
            }
        }
    }

    fun updateUsername(input: String) {
        username = input
        verifyEmail(input)
    }
}