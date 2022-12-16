package me.kevinschildhorn.common.architecture.ui.viewmodel

import me.kevinschildhorn.common.StateFlowAdapter
import me.kevinschildhorn.common.architecture.ui.uistate.LoginUiState
import me.kevinschildhorn.common.architecture.ui.viewmodel.base.CallbackViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@Suppress("unused")
class LoginCallbackViewModel : CallbackViewModel(), KoinComponent {
    override val viewModel: LoginViewModel by inject()

    val uiState: StateFlowAdapter<LoginUiState> by lazy {
        viewModel.uiState.asCallbacks()
    }

    fun updateHostname(hostname: String) = viewModel.updateHostname(hostname)
    fun updateUsername(username: String) = viewModel.updateUsername(username)
    fun updatePassword(password: String) = viewModel.updatePassword(password)

    fun login() = viewModel.login()
}
