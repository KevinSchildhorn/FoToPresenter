package com.kevinschildhorn.fotopresenter.ui.screens.directory

import co.touchlab.kermit.Logger
import com.kevinschildhorn.fotopresenter.UseCaseFactory
import com.kevinschildhorn.fotopresenter.data.DirectoryContents
import com.kevinschildhorn.fotopresenter.data.DirectoryNavigator
import com.kevinschildhorn.fotopresenter.data.Path
import com.kevinschildhorn.fotopresenter.data.repositories.DirectoryRepository
import com.kevinschildhorn.fotopresenter.extension.logLargeTitle
import com.kevinschildhorn.fotopresenter.ui.UiState
import com.kevinschildhorn.fotopresenter.ui.shared.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class DirectoryViewModelNew(
    private val directoryNavigator: DirectoryNavigator,
) : ViewModel(), KoinComponent {

    @Suppress("ktlint:standard:property-naming")
    private val _uiState = MutableStateFlow(DirectoryScreenUIState())
    val uiState: StateFlow<DirectoryScreenUIState> = _uiState
        .combine(directoryNavigator.currentDirectoryContents) { uiState, directoryContents ->
            uiState.copy(
                currentPath = directoryNavigator.currentPath,
                directoryGridUIState = directoryContents.asDirectoryGridUIState,
                state = UiState.SUCCESS,
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = DirectoryScreenUIState()
        )

    fun refreshScreen(){
        viewModelScope.launch(Dispatchers.Default) {
            directoryNavigator.refreshDirectoryContents()
        }
    }
    fun navigateBackToDirectory(directoryIndex: Int) {
        _uiState.update { it.copy(state = UiState.LOADING) }
        viewModelScope.launch(Dispatchers.Default)  {
            directoryNavigator.navigateBackToDirectory(directoryIndex)
        }
    }

    fun navigateIntoDirectory(id: Long) {
        _uiState.update { it.copy(state = UiState.LOADING) }
        viewModelScope.launch(Dispatchers.Default)  {
            directoryNavigator.navigateIntoDirectory(id)
        }
    }

    private val DirectoryContents.asDirectoryGridUIState: DirectoryGridUIState
        get() = DirectoryGridUIState(
            folderStates =
                folders.map {
                    DirectoryGridCellState.Folder(it.name, it.id)
                },
            imageStates =
                images.map {
                    DirectoryGridCellState.Image(it.details, it.name, it.id)
                }.toMutableList(),
        )
}