package com.kevinschildhorn.fotopresenter.ui.viewmodel

import co.touchlab.kermit.Logger
import com.kevinschildhorn.fotopresenter.data.DirectoryContents
import com.kevinschildhorn.fotopresenter.data.network.NetworkDirectoryDetails
import com.kevinschildhorn.fotopresenter.data.network.NetworkHandlerException
import com.kevinschildhorn.fotopresenter.domain.ChangeDirectoryUseCase
import com.kevinschildhorn.fotopresenter.domain.RetrieveDirectoryContentsUseCase
import com.kevinschildhorn.fotopresenter.extension.addPath
import com.kevinschildhorn.fotopresenter.ui.state.DirectoryGridState
import com.kevinschildhorn.fotopresenter.ui.state.DirectoryScreenState
import com.kevinschildhorn.fotopresenter.ui.state.FolderDirectoryGridCellState
import com.kevinschildhorn.fotopresenter.ui.state.ImageDirectoryGridCellState
import com.kevinschildhorn.fotopresenter.ui.state.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import com.kevinschildhorn.fotopresenter.data.State

class DirectoryViewModel(
    private val logger: Logger,
) : ViewModel(), KoinComponent {

    private val _uiState = MutableStateFlow(DirectoryScreenState())
    val uiState: StateFlow<DirectoryScreenState> = _uiState.asStateFlow()

    private val _directoryContentsState = MutableStateFlow(DirectoryContents())

    private val currentPath: String
        get() = uiState.value.currentPath

    fun refreshScreen() {
        updateDirectories()
    }

    fun changeDirectory(directory: NetworkDirectoryDetails) {
        logger.i { "Changing Directory: ${directory.name}" }
        viewModelScope.launch(Dispatchers.Default) {
            val changeDirectoryUseCase: ChangeDirectoryUseCase by inject()
            try {
                logger.i { "Getting New Path" }
                val newPath = changeDirectoryUseCase(currentPath.addPath(directory.name))
                logger.i { "New Path got: $newPath" }
                _uiState.update { it.copy(currentPath = newPath) }
                updateDirectories()
            } catch (e: NetworkHandlerException) {
                logger.e(e) { "Error Occurred Getting new path" }
                _uiState.update {
                    it.copy(
                        state =
                        UiState.ERROR(
                            e.message ?: "An Unknown Network Error Occurred",
                        ),
                    )
                }
            } catch (e: Exception) {
                logger.e(e) { "Something went wrong" }
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
        logger.i { "Updating Directories" }
        _uiState.update { it.copy(state = UiState.LOADING) }
        viewModelScope.launch(Dispatchers.Default) {
            val retrieveDirectoryUseCase: RetrieveDirectoryContentsUseCase by inject()
            logger.i { "Getting Directory Contents" }
            val directoryContents = retrieveDirectoryUseCase(currentPath)
            logger.i { "Got Directory Contents: ${directoryContents.allDirectories.count()}" }
            _directoryContentsState.update { directoryContents }
            _uiState.update {
                it.copy(
                    directoryGridState = directoryContents.asDirectoryGridState,
                    state = UiState.SUCCESS,
                )
            }
        }
    }

    private val DirectoryContents.asDirectoryGridState: DirectoryGridState
        get() =
            DirectoryGridState(
                folderStates = this.folders.map { FolderDirectoryGridCellState(it.name, it.id) },
                imageStates = this.images.map {
                    ImageDirectoryGridCellState(
                        State.IDLE,
                        it.name,
                        it.id
                    )
                }
            )
}
