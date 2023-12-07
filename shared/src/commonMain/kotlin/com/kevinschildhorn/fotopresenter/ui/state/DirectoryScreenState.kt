package com.kevinschildhorn.fotopresenter.ui.state

import androidx.compose.ui.graphics.ImageBitmap
import com.kevinschildhorn.fotopresenter.data.State

data class DirectoryScreenState(
    val currentPath: String = "",
    val directoryGridState: DirectoryGridState = DirectoryGridState(emptyList(), emptyList()),
    override val state: UiState = UiState.IDLE,
) : ScreenState

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
):DirectoryGridCellState

data class ImageDirectoryGridCellState(
    val imageState: State<ImageBitmap>,
    override val name: String,
    override val id: Int,
):DirectoryGridCellState


interface DirectoryGridCellState {
    val name: String
    val id: Int
}