package com.kevinschildhorn.fotopresenter.ui.state

import androidx.compose.ui.graphics.ImageBitmap
import com.kevinschildhorn.fotopresenter.data.State
import com.kevinschildhorn.fotopresenter.extension.getNextIndex
import com.kevinschildhorn.fotopresenter.extension.getPreviousIndex

data class DirectoryScreenState(
    val currentPath: String = "",
    var directoryGridState: DirectoryGridState = DirectoryGridState(emptyList(), mutableListOf()),
    val selectedImageIndex: Int? = null,
    val selectedImage: ImageBitmap? = null,
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

    fun getImageStateByIndex(): State<ImageBitmap>? =
        selectedImageIndex?.let { index ->
            directoryGridState.imageStates.getOrNull(index)?.imageState
        }

    fun getNextImageIndex(): Int? {
        selectedImageIndex?.let {
            return directoryGridState.imageStates.getNextIndex(it)
        } ?: run {
            return null
        }
    }

    fun getPreviousImageIndex(): Int? {
        selectedImageIndex?.let {
            return directoryGridState.imageStates.getPreviousIndex(it)
        } ?: run {
            return null
        }
    }
}

data class DirectoryGridState(
    val folderStates: List<FolderDirectoryGridCellState>,
    val imageStates: List<ImageDirectoryGridCellState>,
) {
    val allStates: List<DirectoryGridCellState>
        get() = folderStates + imageStates
}

data class FolderDirectoryGridCellState(
    override val name: String,
    override val id: Int,
) : DirectoryGridCellState {
    override val actionSheetContexts: List<ActionSheetContext>
        get() = listOf(ActionSheetContext(ActionSheetAction.START_SLIDESHOW, 1))
}

data class ImageDirectoryGridCellState(
    val imageState: State<ImageBitmap>,
    override val name: String,
    override val id: Int,
) : DirectoryGridCellState {

    override val actionSheetContexts: List<ActionSheetContext>
        get() = listOf(ActionSheetContext(ActionSheetAction.NONE, 1))
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