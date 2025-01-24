package com.kevinschildhorn.fotopresenter.ui.screens.directory

import com.kevinschildhorn.fotopresenter.data.ImageSlideshowDetails
import com.kevinschildhorn.fotopresenter.data.Path
import com.kevinschildhorn.fotopresenter.data.network.NetworkDirectoryDetails
import com.kevinschildhorn.fotopresenter.ui.SortingType
import com.kevinschildhorn.fotopresenter.ui.UiState
import com.kevinschildhorn.fotopresenter.ui.screens.common.ActionSheetAction
import com.kevinschildhorn.fotopresenter.ui.screens.common.ActionSheetContext
import com.kevinschildhorn.fotopresenter.ui.screens.common.ScreenState

data class DirectoryScreenUIState(
    val currentPath: Path = Path.EMPTY,
    var directoryGridUIState: DirectoryGridUIState = DirectoryGridUIState(emptyList(), mutableListOf()),
    val slideshowDetails: ImageSlideshowDetails? = null,
    val selectedDirectory: DirectoryGridCellState? = null,
    val sortingType: SortingType = SortingType.NAME_ASC,
    override val state: UiState = UiState.IDLE,
) : ScreenState {
    fun getImageIndexFromId(id: Long): Int = directoryGridUIState.imageStates.indexOfFirst { it.id == id }

    val currentPathList: List<Path>
        get() = currentPath.pathList
}

data class DirectoryGridUIState(
    val folderStates: List<DirectoryGridCellState.Folder>,
    val imageStates: List<DirectoryGridCellState.Image>,
) {
    val allStates: List<DirectoryGridCellState>
        get() = folderStates + imageStates

    override fun toString(): String =
        """
        Directory Grid State:
        Folders: ${folderStates.count()}
            ${folderStates.map { it.toString() }.joinToString(", ")}
        Images: ${imageStates.count()}
            ${imageStates.map { it.toString() }.joinToString(", ")}
        """
}

sealed class DirectoryGridCellState(
    val name: String,
    val id: Long,
    val actionSheetContexts: List<ActionSheetContext>,
) {
    class Folder(name: String, id: Long) : DirectoryGridCellState(
        name,
        id,
        listOf(
            ActionSheetContext(ActionSheetAction.START_SLIDESHOW, 1),
            ActionSheetContext(ActionSheetAction.ADD_DYNAMIC_LOCATION, 2),
        ),
    )

    class Image(val directoryDetails: NetworkDirectoryDetails, name: String, id: Long) : DirectoryGridCellState(
        name,
        id,
        listOf(
            ActionSheetContext(ActionSheetAction.ADD_STATIC_LOCATION, 1),
            ActionSheetContext(ActionSheetAction.ADD_METADATA, 2),
        ),
    )

    override fun toString(): String = "(I:$name:$id)"
}
