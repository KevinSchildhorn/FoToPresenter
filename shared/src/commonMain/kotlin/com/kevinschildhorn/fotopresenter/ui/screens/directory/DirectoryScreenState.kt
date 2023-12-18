package com.kevinschildhorn.fotopresenter.ui.screens.directory

import androidx.compose.ui.graphics.ImageBitmap
import com.kevinschildhorn.fotopresenter.data.ImageSlideshowDetails
import com.kevinschildhorn.fotopresenter.data.State
import com.kevinschildhorn.fotopresenter.ui.UiState
import com.kevinschildhorn.fotopresenter.ui.screens.common.ActionSheetAction
import com.kevinschildhorn.fotopresenter.ui.screens.common.ActionSheetContext
import com.kevinschildhorn.fotopresenter.ui.screens.common.ScreenState

data class DirectoryScreenState(
    val currentPath: String = "",
    var directoryGridState: DirectoryGridState = DirectoryGridState(emptyList(), mutableListOf()),
    val slideshowDetails: ImageSlideshowDetails? = null,
    val loggedIn: Boolean = true,
    override val state: UiState = UiState.IDLE,
) : ScreenState {
    fun copyImageState(
        id: Int,
        state: State<ImageBitmap>,
    ): DirectoryScreenState {
        val list = directoryGridState.imageStates.toMutableList()
        val index = list.indexOfFirst { it.id == id }
        if (index != -1) {
            val element = list[index]
            list[index] =
                element.copy(
                    imageState = state,
                )
        }
        return this.copy(
            directoryGridState =
            directoryGridState.copy(
                imageStates = list,
            ),
        )
    }

    fun getImageIndexFromId(id: Int): Int =
        directoryGridState.imageStates.indexOfFirst { it.id == id }

    val currentPathList: List<String>
        get() = currentPath.split("\\").filter { it.isNotEmpty() }
}

data class DirectoryGridState(
    val folderStates: List<FolderDirectoryGridCellState>,
    val imageStates: List<ImageDirectoryGridCellState>,
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

data class FolderDirectoryGridCellState(
    override val name: String,
    override val id: Int,
) : DirectoryGridCellState {
    override val actionSheetContexts: List<ActionSheetContext>
        get() = listOf(ActionSheetContext(ActionSheetAction.START_SLIDESHOW, 1))

    override fun toString(): String = "(F:$name:$id)"
}

data class ImageDirectoryGridCellState(
    val imageState: State<ImageBitmap>,
    override val name: String,
    override val id: Int,
) : DirectoryGridCellState {
    override val actionSheetContexts: List<ActionSheetContext>
        get() = listOf(
            ActionSheetContext(ActionSheetAction.NONE, 1),
            ActionSheetContext(ActionSheetAction.ADD_STATIC_LOCATION, 1)
        )

    override fun toString(): String = "(I:$name:$id)"
}

interface DirectoryGridCellState {
    val name: String
    val id: Int
    val actionSheetContexts: List<ActionSheetContext>

    val isFolderGridCell: Boolean
        get() = (this is FolderDirectoryGridCellState)

    val isImageGridCell: Boolean
        get() = (this is ImageDirectoryGridCellState)
}
