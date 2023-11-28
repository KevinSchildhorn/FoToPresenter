package com.kevinschildhorn.fotopresenter.ui.state

import com.kevinschildhorn.fotopresenter.data.LoginCredentials

data class LoginUiState(
    val hostname: String = "",
    val port: String = "",
    val username: String = "",
    val password: String = "",
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
) : UiState {

    val isLoginButtonEnabled: Boolean
        get() = hostname.isNotEmpty() &&
                port.isNotEmpty() &&
                username.isNotEmpty() &&
                password.isNotEmpty()

    val asLoginCredentials: LoginCredentials
        get() = LoginCredentials(
            hostname = hostname,
            port = port.toInt(),
            username = username,
            password = password
        )
}
