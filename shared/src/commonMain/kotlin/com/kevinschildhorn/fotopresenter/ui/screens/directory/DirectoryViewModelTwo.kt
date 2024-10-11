package com.kevinschildhorn.fotopresenter.ui.screens.directory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import com.kevinschildhorn.fotopresenter.data.DirectoryEtc
import com.kevinschildhorn.fotopresenter.data.FolderDirectory
import com.kevinschildhorn.fotopresenter.data.ImageDirectory
import com.kevinschildhorn.fotopresenter.data.State
import com.kevinschildhorn.fotopresenter.data.repositories.DirectoryRepository
import com.kevinschildhorn.fotopresenter.data.repositories.PlaylistRepository
import com.kevinschildhorn.fotopresenter.ui.SortingType
import com.kevinschildhorn.fotopresenter.ui.UiState
import com.kevinschildhorn.fotopresenter.ui.screens.common.ScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import kotlin.String

data class DirectoryScreenStateTwo(
    val currentPath: String = "",
    val directoryGridState: DirectoryGridState = DirectoryGridState(emptyList(), mutableListOf()),
    val selectedDirectory: DirectoryGridCellState? = null,
    val currentImageCount: Int = 0,
    val totalImageCount: Int = 0,
    val sortingType: SortingType = SortingType.NAME_ASC,
    override val state: UiState = UiState.IDLE,
) : ScreenState {

    val currentPathList: List<String>
        get() = currentPath.split("\\").filter { it.isNotEmpty() }
}

class DirectoryViewModelTwo(
    private val playlistRepository: PlaylistRepository,
    private val logger: Logger,
    private val directoryRepository: DirectoryRepository,
    private val directoryEtc: DirectoryEtc,
) : ViewModel(), KoinComponent {

    private var currentPath: String = ""

    private val _directoryContentsState = directoryEtc.directoryFlow
    val directoryGridStateFlow: Flow<DirectoryGridState> =
        _directoryContentsState.map { directory ->
            DirectoryGridState(
                folderStates = directory.folders.map { it.asCellState },
                imageStates = directory.images.map { it.asCellState },
            )
        }

    private var selectedDirectory: DirectoryGridCellState? = null
    private var sortingType: SortingType = SortingType.NAME_ASC
    private var state: UiState = UiState.IDLE


    val uiState: Flow<DirectoryScreenStateTwo> = directoryGridStateFlow.map {
        logger.v{ it.toString() }
        DirectoryScreenStateTwo(
            currentPath = currentPath,
            directoryGridState = it,
            selectedDirectory = selectedDirectory,
            currentImageCount = 0,
            totalImageCount = 0,
            sortingType = sortingType,
            state = UiState.SUCCESS,
        )
    }

    fun refreshScreen(){
        if(state != UiState.LOADING) {
            currentPath = "Camera Uploads/Camera"
            updateDirectories()
        }
    }

    private fun updateDirectories() {
        logger.i { "Updating Directories" }
        state = UiState.LOADING
        directoryEtc.refreshDirectories(currentPath, viewModelScope)
    }

    private val FolderDirectory.asCellState: FolderDirectoryGridCellState
        get() = FolderDirectoryGridCellState(name = name, id = id)

    private val ImageDirectory.asCellState: ImageDirectoryGridCellState
        get() {
            val imageState = when{
                (imageBitmap != null) -> State.SUCCESS(imageBitmap)
                image != null -> State.ERROR("")
                else -> State.IDLE
            }
            return ImageDirectoryGridCellState(imageState = imageState, name = name, id = id)
        }
}