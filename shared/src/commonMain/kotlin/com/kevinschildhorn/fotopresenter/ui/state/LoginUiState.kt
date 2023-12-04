package com.kevinschildhorn.fotopresenter.ui.state

import com.kevinschildhorn.fotopresenter.data.LoginCredentials
import com.kevinschildhorn.fotopresenter.ui.compose.common.ButtonState

data class LoginUiState(
    val hostname: String = "",
    val username: String = "",
    val password: String = "",
    val sharedFolder: String = "",
    val shouldAutoConnect: Boolean = false,
    override val state: State = State.IDLE,
) : UiState {
    val isLoginButtonEnabled: Boolean
        get() =
            hostname.isNotEmpty() &&
                sharedFolder.isNotEmpty() &&
                username.isNotEmpty() &&
                password.isNotEmpty()

    val loginbuttonState: ButtonState
        get() =
            when {
                state == State.LOADING -> ButtonState.LOADING
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
            )
}
