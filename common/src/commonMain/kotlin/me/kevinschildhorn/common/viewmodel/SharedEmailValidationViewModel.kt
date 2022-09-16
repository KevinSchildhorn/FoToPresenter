package me.kevinschildhorn.android.viewModel

import kotlinx.coroutines.flow.*
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

    var _validationState: MutableStateFlow<EmailValidationState> =
        MutableStateFlow(EmailValidationState.Empty)
    val validationState: StateFlow<EmailValidationState> = _validationState

    val emailTextFieldState: StateFlow<TextFieldState> = MutableStateFlow(
        TextFieldState(hint = "Email", defaultColor = ColorOption.NORMAL)
    ).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = TextFieldState(hint = "Email", defaultColor = ColorOption.NORMAL)
    )

    fun verifyEmail(email: String) {
        this._validationState.value = when {
            email.isEmpty() -> EmailValidationState.Empty
            simpleEmailRegex.matches(email) -> EmailValidationState.Valid
            else -> EmailValidationState.Invalid
        }
    }
}