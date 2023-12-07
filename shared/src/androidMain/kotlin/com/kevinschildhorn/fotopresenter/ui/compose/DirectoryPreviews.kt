package com.kevinschildhorn.fotopresenter.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.kevinschildhorn.fotopresenter.data.DirectoryContents
import com.kevinschildhorn.fotopresenter.data.FolderDirectory
import com.kevinschildhorn.fotopresenter.data.network.MockNetworkDirectoryDetails
import com.kevinschildhorn.fotopresenter.ui.compose.directory.DirectoryGridCell
import com.kevinschildhorn.fotopresenter.ui.compose.directory.DirectoryGrid
import com.kevinschildhorn.fotopresenter.ui.compose.directory.FolderDirectoryGridCell

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
        FolderDirectoryGridCell("Folder")
    }
}

@Preview
@Composable
fun DirectoryGridPreview() {
    DirectoryGrid(
        DirectoryContents(
            folders = listOf(
                FolderDirectory(MockNetworkDirectoryDetails("Test 1", id = 1)),
                FolderDirectory(MockNetworkDirectoryDetails("Test 2", id = 2)),
                FolderDirectory(MockNetworkDirectoryDetails("Test 3", id = 3)),
                FolderDirectory(MockNetworkDirectoryDetails("Test 4", id = 4)),
                FolderDirectory(MockNetworkDirectoryDetails("Test 6", id = 5)),
                FolderDirectory(MockNetworkDirectoryDetails("Test 5", id = 6)),
            ),
        ),
    ) {
    }
}
