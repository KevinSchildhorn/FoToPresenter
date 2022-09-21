package me.kevinschildhorn.common.viewmodel

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import me.kevinschildhorn.android.viewModel.SharedEmailValidationViewModel
import me.kevinschildhorn.common.StateFlowAdapter
import me.kevinschildhorn.common.businesslogic.CreatingProfileState
import me.kevinschildhorn.common.businesslogic.validation.EmailValidationState
import me.kevinschildhorn.common.businesslogic.validation.EmailValidator
import me.kevinschildhorn.common.businesslogic.validation.PasswordValidationState
import me.kevinschildhorn.common.businesslogic.validation.PasswordValidator
import me.kevinschildhorn.common.uilogic.TextFieldState
import me.kevinschildhorn.common.viewmodel.base.CallbackViewModel
import me.kevinschildhorn.common.viewmodel.ui.ButtonViewModel
import me.kevinschildhorn.common.viewmodel.ui.TextFieldViewModel

@Suppress("unused")
class EmailValidationCallbackViewModel : CallbackViewModel() {
    override val viewModel: SharedEmailValidationViewModel = SharedEmailValidationViewModel()

    val emailTextState = viewModel.emailTextState.uiState.asCallbacks()
    val passwordTextState = viewModel.passwordTextState.uiState.asCallbacks()
    val createProfileButtonState = viewModel.createProfileButtonState.uiState.asCallbacks()


    fun updateEmail(email: String) = viewModel.updateEmail(email)
    fun updatePassword(password: String) = viewModel.updatePassword(password)

    fun createProfile() = viewModel.createProfile()

    fun setEmailFocus(focus: Boolean) {
        viewModel.emailTextState.setFocus(focus)
    }
    fun setPasswordFocus(focus: Boolean) {
        viewModel.passwordTextState.setFocus(focus)
    }
}