package com.kevinschildhorn.fotopresenter.ui.state

import com.kevinschildhorn.fotopresenter.data.LoginCredentials
import com.kevinschildhorn.fotopresenter.ui.compose.common.ButtonState

data class LoginScreenState(
    val hostname: String = "",
    val username: String = "",
    val password: String = "",
    val sharedFolder: String = "",
    val shouldAutoConnect: Boolean = false,
    override val state: UiState = UiState.IDLE,
) : ScreenState {
    val isLoginButtonEnabled: Boolean
        get() =
            hostname.isNotEmpty() &&
                sharedFolder.isNotEmpty() &&
                username.isNotEmpty() &&
                password.isNotEmpty()

    val loginbuttonState: ButtonState
        get() =
            when {
                state == UiState.LOADING -> ButtonState.LOADING
                isLoginButtonEnabled -> ButtonState.ENABLED
                else -> ButtonState.DISABLED
            }

    val asLoginCredentials: LoginCredentials
        get() =
            LoginCredentials(
                hostname = hostname,
                username = username,
                password = password,
                sharedFolder = sharedFolder,
                shouldAutoConnect = shouldAutoConnect,
            )
}
