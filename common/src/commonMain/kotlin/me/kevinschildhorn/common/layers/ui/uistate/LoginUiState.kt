package me.kevinschildhorn.common.layers.ui.uistate

import me.kevinschildhorn.common.layers.data.data.SignInCredentials
import me.kevinschildhorn.common.layers.ui.uistate.base.UiState

data class LoginUiState(
    val address: String = "",
    val username: String = "",
    val password: String = "",
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
) : UiState {

    companion object {
        fun fromCredentials(credentials: SignInCredentials) =
            LoginUiState(
                address = credentials.address,
                username = credentials.username,
                password = credentials.password,
            )
    }
}