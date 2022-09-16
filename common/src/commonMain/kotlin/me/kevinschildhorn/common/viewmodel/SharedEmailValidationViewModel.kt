package me.kevinschildhorn.android.viewModel

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.kevinschildhorn.common.ui.ColorOption
import me.kevinschildhorn.common.uilogic.TextFieldState
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

    private var _emailTextFieldState: MutableStateFlow<TextFieldState> = MutableStateFlow(TextFieldState(hint = "Email", defaultColor = ColorOption.NORMAL))
    val emailTextFieldState: StateFlow<TextFieldState> = _emailTextFieldState
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = TextFieldState(hint = "Email", defaultColor = ColorOption.NORMAL)
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

    private fun refresh(){
        val test = emailTextFieldState.value
        test.updateWithState(validationState.value, email.value)
        _emailTextFieldState.value = test
    }



    // Public

    fun updateUsername(input: String) = verifyEmail(input)

    fun focusChanged(isFocused: Boolean) {
        val test = emailTextFieldState.value
        test.focusChanged(isFocused)
        _emailTextFieldState.value = test
        refresh()
    }
}