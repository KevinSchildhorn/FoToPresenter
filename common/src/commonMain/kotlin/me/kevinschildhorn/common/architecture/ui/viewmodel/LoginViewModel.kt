package me.kevinschildhorn.common.architecture.ui.viewmodel

import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.kevinschildhorn.common.architecture.data.repositories.CredentialsRepository
import me.kevinschildhorn.common.architecture.domain.ConnectToServerUseCase
import me.kevinschildhorn.common.architecture.domain.SaveCredentialsUseCase
import me.kevinschildhorn.common.architecture.ui.uistate.LoginUiState
import me.kevinschildhorn.common.architecture.ui.viewmodel.base.ViewModel
import me.kevinschildhorn.common.connection.ftps.TestingLoginInfo
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/**
[ViewModel] for logging into the FTP Server
 **/
class LoginViewModel(
    private val repository: CredentialsRepository,
) : ViewModel(), KoinComponent {

    private val _uiState = MutableStateFlow(LoginUiState.EMPTY)
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()
    private var fetchJob: Job? = null

    init {
        fetchLoginCredentials()
    }

    fun updateAddress(address: String) = _uiState.update { it.copy(hostname = address) }
    fun updatePort(port: Int) = _uiState.update { it.copy(port = port) }
    fun updateUsername(username: String) = _uiState.update { it.copy(username = username) }
    fun updatePassword(password: String) = _uiState.update { it.copy(password = password) }

    fun login() {
        val connectToServer = ConnectToServerUseCase()
        val defaultInfo = TestingLoginInfo()
        connectToServer()
        val saveCredentials: SaveCredentialsUseCase by inject()
        _uiState.update { it.copy(isLoading = true) }
        with(uiState.value) {
            val success = saveCredentials(hostname, username, password)
            _uiState.update {
                it.copy(
                    isLoading = false,
                    errorMessage = if (success) null else "Error"
                )
            }
        }
    }

    fun fetchLoginCredentials() {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            val credentials = repository.fetchCredentials()
            _uiState.update {
                LoginUiState.fromCredentials(credentials)
            }
        }
    }
}
