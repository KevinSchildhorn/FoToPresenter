package com.kevinschildhorn.fotopresenter.ui.state

import androidx.compose.ui.graphics.ImageBitmap
import com.kevinschildhorn.fotopresenter.data.State

data class DirectoryScreenState(
    val currentPath: String = "",
    var directoryGridState: DirectoryGridState = DirectoryGridState(emptyList(), mutableListOf()),
    var selectedImage: State<ImageBitmap> = State.IDLE,
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
) : DirectoryGridCellState

data class ImageDirectoryGridCellState(
    val imageState: State<ImageBitmap>,
    override val name: String,
    override val id: Int,
) : DirectoryGridCellState

interface DirectoryGridCellState {
    val name: String
    val id: Int

    val isFolderGridCell: Boolean
        get() = (this is FolderDirectoryGridCellState)

    val isImageGridCell: Boolean
        get() = (this is ImageDirectoryGridCellState)
}
