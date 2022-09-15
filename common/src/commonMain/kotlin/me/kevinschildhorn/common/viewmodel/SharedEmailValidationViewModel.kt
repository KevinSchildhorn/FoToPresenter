package me.kevinschildhorn.android.viewModel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import me.kevinschildhorn.common.viewmodel.base.ViewModel


enum class EmailValidationState {
    Empty,
    Valid,
    Invalid
}

open class SharedEmailValidationViewModel : ViewModel() {
    private val simpleEmailRegex = "^\\S+@\\S+\\.\\S+\$".toRegex()

    private val _email: MutableStateFlow<String> = MutableStateFlow("")
    val email = _email.asStateFlow()

    var validationState: MutableStateFlow<EmailValidationState> =
        MutableStateFlow(EmailValidationState.Empty)
        private set


    fun verifyEmail(email: String) {
        this._email.value = email
        this.validationState.value = when {
            email.isEmpty() -> EmailValidationState.Empty
            simpleEmailRegex.matches(email) -> EmailValidationState.Valid
            else -> EmailValidationState.Invalid
        }
    }
}