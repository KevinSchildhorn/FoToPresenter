package com.kevinschildhorn.fotopresenter.ui.viewmodel

import co.touchlab.kermit.Logger
import com.kevinschildhorn.fotopresenter.data.repositories.CredentialsRepository
import com.kevinschildhorn.fotopresenter.domain.AutoConnectUseCase
import com.kevinschildhorn.fotopresenter.domain.ConnectToServerUseCase
import com.kevinschildhorn.fotopresenter.domain.SaveCredentialsUseCase
import com.kevinschildhorn.fotopresenter.ui.state.LoginUiState
import com.kevinschildhorn.fotopresenter.ui.state.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class LoginViewModel(
    private val logger: Logger,
    private val credentialsRepository: CredentialsRepository,
) : ViewModel(), KoinComponent {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    init {
        val credentials = credentialsRepository.fetchCredentials()
        _uiState.update {
            it.copy(
                hostname = credentials.hostname,
                username = credentials.username,
                password = credentials.password,
                sharedFolder = credentials.sharedFolder,
                shouldAutoConnect = credentials.shouldAutoConnect,
            )
        }
        if (credentials.isComplete && credentials.shouldAutoConnect) {
            attemptAutoLogin()
        }
    }

    fun updateHost(hostname: String) {
        _uiState.update { it.copy(hostname = hostname) }
    }

    fun updateUsername(username: String) {
        _uiState.update { it.copy(username = username) }
    }

    fun updatePassword(password: String) {
        _uiState.update { it.copy(password = password) }
    }

    fun updateSharedFolder(sharedFolder: String) {
        _uiState.update { it.copy(sharedFolder = sharedFolder) }
    }

    fun updateShouldAutoConnect(shouldAutoConnect: Boolean) {
        _uiState.update { it.copy(shouldAutoConnect = shouldAutoConnect) }
    }

    fun login() {
        logger.i { "Logging In" }

        _uiState.update { it.copy(state = State.LOADING) }

        val connectToServer: ConnectToServerUseCase by inject()
        viewModelScope.launch(Dispatchers.Default) {
            val result =
                connectToServer(
                    _uiState.value.asLoginCredentials,
                )

            if (!result) {
                _uiState.update { it.copy(state = State.ERROR("")) }
                return@launch
            } else {
                _uiState.update { it.copy(state = State.SUCCESS) }
                val saveCredentials: SaveCredentialsUseCase by inject()
                saveCredentials(_uiState.value.asLoginCredentials)
            }
        }
    }

    private fun attemptAutoLogin() {
        logger.i { "Attempting To Auto Login" }
        viewModelScope.launch {
            val autoConnectUseCase: AutoConnectUseCase by inject()
            if (autoConnectUseCase()) {
                // TODO
            }
        }
    }
}
