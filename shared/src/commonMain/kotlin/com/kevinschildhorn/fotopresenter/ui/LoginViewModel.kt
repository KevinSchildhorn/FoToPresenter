package com.kevinschildhorn.fotopresenter.ui

import com.kevinschildhorn.fotopresenter.data.LoginUiState
import com.rickclephas.kmm.viewmodel.KMMViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel : KMMViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun updateHost(host: String) {
        _uiState.value = _uiState.value.copy(hostName = host)
    }

    fun updateUsername(username: String) {
        _uiState.value = _uiState.value.copy(username = username)
    }

    fun updatePassword(password: String) {
        _uiState.value = _uiState.value.copy(password = password)
    }

    fun login() {
        _uiState.value = _uiState.value.copy(isSuccess = true)
    }
}