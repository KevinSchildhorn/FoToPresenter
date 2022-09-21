package me.kevinschildhorn.android.viewModel

import kotlinx.coroutines.flow.*
import me.kevinschildhorn.common.extensions.stateFrom
import me.kevinschildhorn.common.uilogic.enums.TextFieldState
import me.kevinschildhorn.common.uilogic.enums.asTextFieldState
import me.kevinschildhorn.common.businesslogic.validation.EmailValidationState
import me.kevinschildhorn.common.businesslogic.validation.EmailValidator
import me.kevinschildhorn.common.businesslogic.validation.PasswordValidationState
import me.kevinschildhorn.common.businesslogic.validation.PasswordValidator
import me.kevinschildhorn.common.viewmodel.base.ViewModel

open class SharedEmailValidationViewModel : ViewModel() {

    // Email
    private var _email: MutableStateFlow<String> = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private var _emailValidationState: MutableStateFlow<EmailValidationState> =
        MutableStateFlow(EmailValidationState.Empty)
    private val emailValidationState: StateFlow<EmailValidationState> = _emailValidationState

    private var _emailTextFieldState: MutableStateFlow<TextFieldState> =
        MutableStateFlow(TextFieldState(hint = "Email"))
    val emailTextFieldState: StateFlow<TextFieldState> = _emailTextFieldState
        .stateFrom(viewModelScope)

    // Password
    private var _password: MutableStateFlow<String> = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private var _passwordValidationState: MutableStateFlow<PasswordValidationState> =
        MutableStateFlow(PasswordValidationState.Empty)
    private val passwordValidationState: StateFlow<PasswordValidationState> =
        _passwordValidationState

    private var _passwordTextFieldState: MutableStateFlow<TextFieldState> =
        MutableStateFlow(TextFieldState(hint = "Password"))
    val passwordTextFieldState: StateFlow<TextFieldState> = _passwordTextFieldState
        .stateFrom(viewModelScope)

    private fun verifyEmail(email: String) {
        _emailValidationState.value = EmailValidator.verifyEmail(email)
        _email.value = email
        refresh()
    }

    private fun verifyPassword(password: String) {
        _passwordValidationState.value = PasswordValidator.verifyPassword(password)
        _password.value = password
        refresh()
    }

    private fun refresh() {
        _emailTextFieldState.value =
            emailTextFieldState.value.updateWithState(
                emailValidationState.value.asTextFieldState,
                email.value
            )
        _passwordTextFieldState.value =
            passwordTextFieldState.value.updateWithState(
                passwordValidationState.value.asTextFieldState,
                password.value
            )
    }

    // Public

    fun updateEmail(input: String) = verifyEmail(input)
    fun updatePassword(input: String) = verifyPassword(input)

    fun emailFocusChanged(isFocused: Boolean) {
        _emailTextFieldState.value = emailTextFieldState.value.focusChanged(isFocused)
        refresh()
    }

    fun passwordFocusChanged(isFocused: Boolean) {
        _passwordTextFieldState.value = passwordTextFieldState.value.focusChanged(isFocused)
        refresh()
    }
}