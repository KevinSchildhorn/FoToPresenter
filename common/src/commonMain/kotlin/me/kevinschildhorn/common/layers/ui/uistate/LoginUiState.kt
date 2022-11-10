package me.kevinschildhorn.common.layers.ui.uistate

import me.kevinschildhorn.common.layers.data.data.SignInCredentials
import me.kevinschildhorn.common.layers.ui.uistate.base.UiState

data class LoginUiState(
    override var content: SignInCredentials,
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
) : UiState<SignInCredentials> {

    var address: String
        get() = content.address
        set(value) {
            content = content.copy(address = value)
        }

    var username: String
        get() = content.username
        set(value) {
            content = content.copy(username = value)
        }

    var password: String
        get() = content.password
        set(value) {
            content = content.copy(password = value)
        }
    
    companion object {
        fun fromCredentials(credentials: SignInCredentials) =
            LoginUiState(
                content = credentials
            )
    }
}