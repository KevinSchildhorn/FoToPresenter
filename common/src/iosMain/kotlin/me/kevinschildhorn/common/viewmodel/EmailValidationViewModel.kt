package me.kevinschildhorn.common.viewmodel

import me.kevinschildhorn.android.viewModel.SharedEmailValidationViewModel
import me.kevinschildhorn.common.StateFlowAdapter
import me.kevinschildhorn.common.uilogic.enums.TextFieldState
import me.kevinschildhorn.common.viewmodel.base.CallbackViewModel

@Suppress("unused")
class EmailValidationCallbackViewModel : CallbackViewModel() {
    override val viewModel: SharedEmailValidationViewModel = SharedEmailValidationViewModel()

    val email: StateFlowAdapter<String> by lazy { viewModel.email.asCallbacks() }
    val emailTextFieldState: StateFlowAdapter<TextFieldState> by lazy { viewModel.emailTextFieldState.asCallbacks() }

    fun updateUsername(input: String) = viewModel.updateUsername(input)
    fun focusChanged(isFocused: Boolean) = viewModel.focusChanged(isFocused)
}