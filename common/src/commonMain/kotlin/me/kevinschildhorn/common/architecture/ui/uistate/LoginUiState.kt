package me.kevinschildhorn.common.architecture.ui.uistate

import me.kevinschildhorn.common.architecture.ui.uistate.base.UiState

data class LoginUiState(
    val hostname: String,
    val port: String,
    val username: String,
    val password: String,
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
) : UiState {

    val isLoginButtonEnabled: Boolean
        get() = hostname.isNotEmpty() &&
            port.isNotEmpty() &&
            username.isNotEmpty() &&
            password.isNotEmpty()

    companion object {

        val EMPTY: LoginUiState
            get() = LoginUiState(
                hostname = "",
                port = "",
                username = "",
                password = "",
            )
    }
}
