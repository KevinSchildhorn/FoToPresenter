package com.kevinschildhorn.fotopresenter.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.kevinschildhorn.fotopresenter.data.State
import com.kevinschildhorn.fotopresenter.ui.screens.directory.composables.DirectoryGridCell
import com.kevinschildhorn.fotopresenter.ui.screens.directory.composables.DirectoryGrid
import com.kevinschildhorn.fotopresenter.ui.screens.directory.composables.FolderDirectoryGridCell
import com.kevinschildhorn.fotopresenter.ui.screens.directory.DirectoryGridState
import com.kevinschildhorn.fotopresenter.ui.screens.directory.FolderDirectoryGridCellState
import com.kevinschildhorn.fotopresenter.ui.screens.directory.ImageDirectoryGridCellState

@Preview
@Composable
fun BaseDirectoryPreview() {
    Column {
        DirectoryGridCell() {}
    }
}

@Preview
@Composable
fun FolderDirectoryEmptyPreview() {
    Column {
        FolderDirectoryGridCell(FolderDirectoryGridCellState("Hello",0))
    }
}

@Preview
@Composable
fun DirectoryGridPreview() {
    DirectoryGrid(
        directoryContent = DirectoryGridState(
            folderStates = listOf(
                FolderDirectoryGridCellState("Hello",0),
            ),
            imageStates = mutableListOf(
                ImageDirectoryGridCellState(State.IDLE,"Hello", 1)
            )
        ),
        onFolderPressed = {},
        onImageDirectoryPressed = {},
        onStartSlideshow = {},
    )
}
