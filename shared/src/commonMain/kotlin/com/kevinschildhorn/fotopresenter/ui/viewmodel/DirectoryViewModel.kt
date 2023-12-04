package com.kevinschildhorn.fotopresenter.ui.viewmodel

import co.touchlab.kermit.Logger
import com.kevinschildhorn.fotopresenter.domain.RetrieveDirectoryUseCase
import com.kevinschildhorn.fotopresenter.ui.state.DirectoryUiState
import com.kevinschildhorn.fotopresenter.ui.state.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DirectoryViewModel(
    private val logger: Logger,
) : ViewModel(), KoinComponent {
    private val _uiState = MutableStateFlow(DirectoryUiState())
    val uiState: StateFlow<DirectoryUiState> = _uiState.asStateFlow()


    fun refreshScreen(){
        updateDirectories()
    }

    private fun updateDirectories() {
        _uiState.update { it.copy(state = State.LOADING) }
        viewModelScope.launch(Dispatchers.Default) {
            val retrieveDirectoryUseCase: RetrieveDirectoryUseCase by inject()
            val directories = retrieveDirectoryUseCase()
            _uiState.update {
                it.copy(
                    directories = directories,
                    state = State.SUCCESS
                )
            }
        }
    }
}