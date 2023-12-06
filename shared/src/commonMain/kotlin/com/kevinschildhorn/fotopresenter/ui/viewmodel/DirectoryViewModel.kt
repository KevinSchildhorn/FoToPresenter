package com.kevinschildhorn.fotopresenter.ui.viewmodel

import co.touchlab.kermit.Logger
import com.kevinschildhorn.fotopresenter.data.network.NetworkDirectory
import com.kevinschildhorn.fotopresenter.data.network.NetworkHandlerException
import com.kevinschildhorn.fotopresenter.domain.ChangeDirectoryUseCase
import com.kevinschildhorn.fotopresenter.domain.RetrieveDirectoryContentsUseCase
import com.kevinschildhorn.fotopresenter.extension.addPath
import com.kevinschildhorn.fotopresenter.ui.state.DirectoryScreenState
import com.kevinschildhorn.fotopresenter.ui.state.State
import com.kevinschildhorn.fotopresenter.ui.state.UiState
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
    private val _uiState = MutableStateFlow(DirectoryScreenState())
    val uiState: StateFlow<DirectoryScreenState> = _uiState.asStateFlow()

    private val currentPath: String
        get() = uiState.value.currentPath

    fun refreshScreen() {
        updateDirectories()
    }

    fun changeDirectory(directory: NetworkDirectory) {
        viewModelScope.launch(Dispatchers.Default) {
            val changeDirectoryUseCase: ChangeDirectoryUseCase by inject()
            try {
                changeDirectoryUseCase(currentPath.addPath(directory.name))?.let { newPath ->
                    _uiState.update { it.copy(currentPath = newPath) }
                }
                updateDirectories()
            } catch (e: NetworkHandlerException) {
                _uiState.update {
                    it.copy(
                        state =
                        UiState.ERROR(
                            e.message ?: "An Unknown Network Error Occurred",
                        ),
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        state =
                        UiState.ERROR(
                            e.message ?: "Something Went Wrong",
                        ),
                    )
                }
            }
        }
    }

    private fun updateDirectories() {
        _uiState.update { it.copy(state = UiState.LOADING) }
        viewModelScope.launch(Dispatchers.Default) {
            val retrieveDirectoryUseCase: RetrieveDirectoryContentsUseCase by inject()
            val directoryContents = retrieveDirectoryUseCase(currentPath)
            _uiState.update {
                it.copy(
                    directoryContents = directoryContents,
                    state = UiState.SUCCESS,
                )
            }
        }
    }
}
