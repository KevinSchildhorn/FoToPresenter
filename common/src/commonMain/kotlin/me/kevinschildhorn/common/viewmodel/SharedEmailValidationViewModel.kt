package me.kevinschildhorn.android.viewModel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import me.kevinschildhorn.common.viewmodel.base.ViewModel


enum class EmailValidationState {
    Empty,
    Valid,
    Invalid
}

open class SharedEmailValidationViewModel : ViewModel() {
    private val simpleEmailRegex = "^\\S+@\\S+\\.\\S+\$".toRegex()

    var _validationState: MutableStateFlow<EmailValidationState> =
        MutableStateFlow(EmailValidationState.Empty)
    val validationState: StateFlow<EmailValidationState> = _validationState

    fun verifyEmail(email: String) {
        this._validationState.value = when {
            email.isEmpty() -> EmailValidationState.Empty
            simpleEmailRegex.matches(email) -> EmailValidationState.Valid
            else -> EmailValidationState.Invalid
        }
    }
}