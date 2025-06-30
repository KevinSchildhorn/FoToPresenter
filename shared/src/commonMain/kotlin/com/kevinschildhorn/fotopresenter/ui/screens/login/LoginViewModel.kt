package com.kevinschildhorn.fotopresenter.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import com.kevinschildhorn.fotopresenter.data.network.NetworkHandler
import com.kevinschildhorn.fotopresenter.data.repositories.CredentialsRepository
import com.kevinschildhorn.fotopresenter.ui.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class LoginViewModel(
    private val logger: Logger,
    private val credentialsRepository: CredentialsRepository,
    private val networkHandler: NetworkHandler,
) : ViewModel(),
    KoinComponent {
    private val _uiState = MutableStateFlow(LoginScreenState())
    val uiState: StateFlow<LoginScreenState> = _uiState.asStateFlow()

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
        _uiState.update { it.copy(hostname = hostname, state = UiState.IDLE) }
    }

    fun updateUsername(username: String) {
        _uiState.update { it.copy(username = username, state = UiState.IDLE) }
    }

    fun updatePassword(password: String) {
        _uiState.update { it.copy(password = password, state = UiState.IDLE) }
    }

    fun updateSharedFolder(sharedFolder: String) {
        _uiState.update { it.copy(sharedFolder = sharedFolder, state = UiState.IDLE) }
    }

    fun updateShouldAutoConnect(shouldAutoConnect: Boolean) {
        _uiState.update { it.copy(shouldAutoConnect = shouldAutoConnect, state = UiState.IDLE) }
    }

    fun login() {
        logger.i { "Logging In" }

        _uiState.update { it.copy(state = UiState.LOADING) }

        viewModelScope.launch(Dispatchers.Default) {
            logger.i { "Connecting To Server With Credentials" }

            val result =
                try {
                    logger.i { "Connecting to Client ${_uiState.value.hostname}" }
                    networkHandler.connect(_uiState.value.asLoginCredentials)
                } catch (e: Exception) {
                    logger.e(e) { "Something went wrong" }
                    false
                }

            if (!result) {
                logger.w { "Error Occurred Connecting to Server" }
                _uiState.update { it.copy(state = UiState.ERROR("")) }
                return@launch
            } else {
                logger.i { "Successfully Logged In" }
                _uiState.update { it.copy(state = UiState.SUCCESS) }
                logger.i { "Saving Credentials" }
                logger.i { "State is ${uiState.value}" }
                with(_uiState.value.asLoginCredentials) {
                    logger.i { "Saving Credentials" }
                    credentialsRepository.saveCredentials(
                        hostname = hostname,
                        username = username,
                        password = password,
                        sharedFolder = sharedFolder,
                        shouldAutoConnect = shouldAutoConnect,
                    )
                }
            }
        }
    }

    fun setLoggedOut() {
        _uiState.update { it.copy(state = UiState.IDLE) }
    }

    private fun attemptAutoLogin() {
        logger.i { "Attempting To Auto Login" }
        viewModelScope.launch(Dispatchers.Default) {
            try {
                logger.d { "Fetching Auto-Connect Credentials" }
                val credentials = credentialsRepository.fetchCredentials()
                logger.d { "Connecting to the client with auto-connect:${credentials.shouldAutoConnect}" }
                networkHandler.connect(credentials)
                _uiState.update { it.copy(state = UiState.SUCCESS) }
            } catch (e: Exception) {
                logger.e(e) { "Could not connect" }
                _uiState.update {
                    it.copy(
                        state =
                            UiState.ERROR(
                                e.message ?: "Couldn't Auto Login",
                            ),
                    )
                }
            }
        }
    }
}
