package me.kevinschildhorn.common.layers.ui.viewmodel

import me.kevinschildhorn.common.layers.ui.viewmodel.base.CallbackViewModel

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