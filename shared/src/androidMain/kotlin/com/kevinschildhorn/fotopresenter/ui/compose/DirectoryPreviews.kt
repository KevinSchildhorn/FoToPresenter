package com.kevinschildhorn.fotopresenter.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.kevinschildhorn.fotopresenter.data.Path
import com.kevinschildhorn.fotopresenter.data.network.MockNetworkDirectoryDetails
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.AdvancedSearchDialog
import com.kevinschildhorn.fotopresenter.ui.screens.directory.DirectoryGridCellUIState
import com.kevinschildhorn.fotopresenter.ui.screens.directory.DirectoryGridUIState
import com.kevinschildhorn.fotopresenter.ui.screens.directory.composables.grid.DirectoryGrid
import com.kevinschildhorn.fotopresenter.ui.screens.directory.composables.grid.DirectoryGridCell
import com.kevinschildhorn.fotopresenter.ui.screens.directory.composables.grid.FolderDirectoryGridCell
import com.kevinschildhorn.fotopresenter.ui.screens.directory.composables.navbar.DirectoryNavigationBar
import com.kevinschildhorn.fotopresenter.ui.screens.directory.composables.navbar.DirectoryNavigationItem

@Preview
@Composable
fun BaseDirectoryPreview() {
    Column {
        DirectoryGridCell {}
    }
}

@Preview
@Composable
fun FolderDirectoryEmptyPreview() {
    Column {
        FolderDirectoryGridCell(DirectoryGridCellUIState.Folder("Hello", 0))
    }
}

@Preview
@Composable
fun DirectoryGridPreview() {
    DirectoryGrid(
        directoryContent =
            DirectoryGridUIState(
                currentPath = Path.EMPTY,
                folderStates =
                    listOf(
                        DirectoryGridCellUIState.Folder("Hello", 0),
                    ),
                imageStates =
                    mutableListOf(
                        DirectoryGridCellUIState.Image(MockNetworkDirectoryDetails(), "Hello", 1),
                    ),
            ),
        onFolderPressed = {},
        onImageDirectoryPressed = {},
        onActionSheet = {},
    )
}

@Preview
@Composable
fun DirectoryNavigationItemPreview() {
    DirectoryNavigationItem("Photos") {
    }
}

@Preview
@Composable
fun DirectoryNavigationBarPreview() {
    DirectoryNavigationBar(
        directories =
            listOf(
                "Photos1",
                "Subfolder1",
                "Photos2",
                "Subfolder2",
                "Photos3",
                "Subfolder3",
            ).map { Path(it) },
        {},
        {},
    )
}

@Preview
@Composable
private fun SortDialogPreview() {
    AdvancedSearchDialog(
        onDismissRequest = {},
        onConfirmation = {},
    )
}
