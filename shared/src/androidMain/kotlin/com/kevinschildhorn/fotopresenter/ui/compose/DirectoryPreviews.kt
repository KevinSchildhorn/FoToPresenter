package com.kevinschildhorn.fotopresenter.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.kevinschildhorn.fotopresenter.data.DirectoryContents
import com.kevinschildhorn.fotopresenter.data.FolderDirectory
import com.kevinschildhorn.fotopresenter.data.State
import com.kevinschildhorn.fotopresenter.data.network.MockNetworkDirectoryDetails
import com.kevinschildhorn.fotopresenter.ui.compose.directory.DirectoryGridCell
import com.kevinschildhorn.fotopresenter.ui.compose.directory.DirectoryGrid
import com.kevinschildhorn.fotopresenter.ui.compose.directory.FolderDirectoryGridCell
import com.kevinschildhorn.fotopresenter.ui.state.DirectoryGridState
import com.kevinschildhorn.fotopresenter.ui.state.FolderDirectoryGridCellState
import com.kevinschildhorn.fotopresenter.ui.state.ImageDirectoryGridCellState

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
    )
}
