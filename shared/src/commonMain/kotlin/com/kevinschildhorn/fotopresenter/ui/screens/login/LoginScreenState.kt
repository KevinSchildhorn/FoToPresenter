package com.kevinschildhorn.fotopresenter.ui.screens.login

import com.kevinschildhorn.fotopresenter.data.login.LoginCredentials
import com.kevinschildhorn.fotopresenter.ui.UiState
import com.kevinschildhorn.fotopresenter.ui.screens.common.ScreenState
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.ButtonState

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

    val loginButtonState: ButtonState
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
