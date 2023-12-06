package com.kevinschildhorn.fotopresenter.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.kevinschildhorn.fotopresenter.data.DirectoryContents
import com.kevinschildhorn.fotopresenter.data.FolderDirectoryContent
import com.kevinschildhorn.fotopresenter.data.network.MockNetworkDirectory
import com.kevinschildhorn.fotopresenter.ui.compose.directory.BaseDirectory
import com.kevinschildhorn.fotopresenter.ui.compose.directory.DirectoryGrid
import com.kevinschildhorn.fotopresenter.ui.compose.directory.FolderDirectoryItem

@Preview
@Composable
fun BaseDirectoryPreview() {
    Column {
        BaseDirectory() {}
    }
}

@Preview
@Composable
fun FolderDirectoryEmptyPreview() {
    Column {
        FolderDirectoryItem("Folder")
    }
}


@Preview
@Composable
fun DirectoryGridPreview() {
    DirectoryGrid(
        DirectoryContents(
            folders = listOf(
                FolderDirectoryContent(MockNetworkDirectory("Test 1", id = 1)),
                FolderDirectoryContent(MockNetworkDirectory("Test 2", id = 2)),
                FolderDirectoryContent(MockNetworkDirectory("Test 3", id = 3)),
                FolderDirectoryContent(MockNetworkDirectory("Test 4", id = 4)),
                FolderDirectoryContent(MockNetworkDirectory("Test 6", id = 5)),
                FolderDirectoryContent(MockNetworkDirectory("Test 5", id = 6)),
            )
        )
    ) {

    }

}
