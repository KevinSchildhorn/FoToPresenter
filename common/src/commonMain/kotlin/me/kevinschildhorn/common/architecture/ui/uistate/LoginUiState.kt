package me.kevinschildhorn.common.architecture.ui.uistate

import me.kevinschildhorn.common.architecture.data.data.SignInCredentials
import me.kevinschildhorn.common.architecture.ui.uistate.base.UiState

data class LoginUiState(
    val hostname: String,
    val port: String,
    val username: String,
    val password: String,
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
) : UiState {

    companion object {
        fun fromCredentials(credentials: SignInCredentials) =
            LoginUiState(
                hostname = credentials.address,
                port = credentials.port,
                username = credentials.username,
                password = credentials.password,
            )

        val EMPTY: LoginUiState
            get() = LoginUiState(
                hostname = "",
                port = "",
                username = "",
                password = "",
            )
    }
}
