package com.kevinschildhorn.fotopresenter.ui

import com.kevinschildhorn.fotopresenter.ui.state.LoginUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    init {
        attemptAutoLogin()
    }

    fun updateHost(host: String) {
        _uiState.value = _uiState.value.copy(hostname = host)
    }

    fun updateUsername(username: String) {
        _uiState.value = _uiState.value.copy(username = username)
    }

    fun updatePassword(password: String) {
        _uiState.value = _uiState.value.copy(password = password)
    }

    fun login() {
        /*
        val connectToServer = ConnectToServerUseCase()
        viewModelScope.launch(Dispatchers.Default) {
            val result = connectToServer(
                _uiState.value.asLoginCredentials
            )
        }
        //val saveCredentials: SaveCredentialsUseCase by inject()
        _uiState.value = _uiState.value.copy(isLoading = true)

         */
    }

    private fun attemptAutoLogin() {
        viewModelScope.launch {
            /*
            val autoConnectUseCase: AutoConnectUseCase by inject()
            if (autoConnectUseCase()) {
                // TODO
            }*/
        }
    }
}