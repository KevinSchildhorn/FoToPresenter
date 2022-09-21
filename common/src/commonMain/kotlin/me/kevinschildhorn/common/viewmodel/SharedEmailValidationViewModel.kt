package me.kevinschildhorn.android.viewModel

import kotlinx.coroutines.flow.*
import me.kevinschildhorn.common.uilogic.enums.TextFieldState
import me.kevinschildhorn.common.viewmodel.base.ViewModel


enum class EmailValidationState {
    Empty,
    Valid,
    Invalid
}

open class SharedEmailValidationViewModel : ViewModel() {
    private val simpleEmailRegex = "^\\S+@\\S+\\.\\S+\$".toRegex()

    private var _email: MutableStateFlow<String> = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private var _validationState: MutableStateFlow<EmailValidationState> =
        MutableStateFlow(EmailValidationState.Empty)
    private val validationState: StateFlow<EmailValidationState> = _validationState

    private var _emailTextFieldState: MutableStateFlow<TextFieldState> =
        MutableStateFlow(TextFieldState(hint = "Email"))
    val emailTextFieldState: StateFlow<TextFieldState> = _emailTextFieldState
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = TextFieldState(hint = "Email")
        )

    private fun verifyEmail(email: String) {
        val nextState = when {
            email.isEmpty() -> EmailValidationState.Empty
            simpleEmailRegex.matches(email) -> EmailValidationState.Valid
            else -> EmailValidationState.Invalid
        }
        _validationState.value = nextState
        _email.value = email
        refresh()
    }

    private fun refresh() {
        _emailTextFieldState.value =
            emailTextFieldState.value.updateWithState(validationState.value, email.value)
    }

    // Public

    fun updateUsername(input: String) = verifyEmail(input)

    fun focusChanged(isFocused: Boolean) {
        _emailTextFieldState.value = emailTextFieldState.value.focusChanged(isFocused)
        refresh()
    }
}