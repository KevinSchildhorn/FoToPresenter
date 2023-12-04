package com.kevinschildhorn.fotopresenter.ui.viewmodel

import co.touchlab.kermit.Logger
import com.kevinschildhorn.fotopresenter.data.network.NetworkDirectory
import com.kevinschildhorn.fotopresenter.data.network.NetworkHandlerException
import com.kevinschildhorn.fotopresenter.domain.ChangeDirectoryUseCase
import com.kevinschildhorn.fotopresenter.domain.RetrieveDirectoryUseCase
import com.kevinschildhorn.fotopresenter.domain.RetrievePhotosFromDirectoryUseCase
import com.kevinschildhorn.fotopresenter.ui.state.DirectoryContents
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


    fun refreshScreen() {
        updateDirectories()
    }

    fun changeDirectory(directory: NetworkDirectory) {
        viewModelScope.launch(Dispatchers.Default) {
            val changeDirectoryUseCase: ChangeDirectoryUseCase by inject()
            try {
                changeDirectoryUseCase(directory.name)
                updateDirectories()
            } catch (e: NetworkHandlerException) {
                _uiState.update {
                    it.copy(
                        state = State.ERROR(
                            e.message ?: "An Unknown Network Error Occurred"
                        )
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        state = State.ERROR(
                            e.message ?: "Something Went Wrong"
                        )
                    )
                }
            }
        }
    }

    private fun updateDirectories() {
        _uiState.update { it.copy(state = State.LOADING) }
        viewModelScope.launch(Dispatchers.Default) {
            val retrieveDirectoryUseCase: RetrieveDirectoryUseCase by inject()
            val retrievePhotosFromDirectoryUseCase: RetrievePhotosFromDirectoryUseCase by inject()

            // TODO: RENAME SMBJ AS DATA SOURCE AND ADD REPOSITORY
            val directories = retrieveDirectoryUseCase().map { DirectoryContents(it) }
            val photos =
                retrievePhotosFromDirectoryUseCase().map { DirectoryContents(it.first, it.second) }

            val directoryContents = directories.toMutableList()
            directoryContents.addAll(photos)

            _uiState.update {
                it.copy(
                    directories = directoryContents,
                    state = State.SUCCESS
                )
            }
        }
    }
}