package me.kevinschildhorn.common.layers.ui.viewmodel

import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.kevinschildhorn.common.layers.data.data.SignInCredentials
import me.kevinschildhorn.common.layers.data.repositories.CredentialsRepository
import me.kevinschildhorn.common.layers.ui.viewmodel.base.ViewModel
import me.kevinschildhorn.common.layers.domain.SaveCredentialsUseCase
import me.kevinschildhorn.common.layers.ui.uistate.LoginUiState
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/**
[ViewModel] for logging into the FTP Server
 **/
class LoginViewModel(
    private val repository: CredentialsRepository,
) : ViewModel(), KoinComponent {

    private val _uiState = MutableStateFlow(LoginUiState(SignInCredentials.EMPTY))
    val uiState = _uiState.asStateFlow()
    private var fetchJob: Job? = null

    var address: String
        get() = uiState.value.address
        set(value) {
            _uiState.update {
                it.apply { address = value }
            }
        }

    var username: String
        get() = uiState.value.username
        set(value) {
            _uiState.update {
                it.apply { username = value }
            }
        }

    var password: String
        get() = uiState.value.password
        set(value) {
            _uiState.update {
                it.apply { password = value }
            }
        }

    init {
        fetchLoginCredentials()
    }

    fun login() {
        val saveCredentials: SaveCredentialsUseCase by inject()
        with(uiState.value.content) {
            _uiState.update { it.copy(isLoading = true) }
            val success = saveCredentials(address, username, password)
            _uiState.update { it.copy(isLoading = false, errorMessage = if (success) null else "Error") }
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