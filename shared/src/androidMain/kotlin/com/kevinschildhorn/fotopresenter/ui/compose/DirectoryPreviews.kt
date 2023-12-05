package com.kevinschildhorn.fotopresenter.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.kevinschildhorn.fotopresenter.data.network.MockNetworkDirectory
import com.kevinschildhorn.fotopresenter.ui.compose.directory.BaseDirectory
import com.kevinschildhorn.fotopresenter.ui.compose.directory.DirectoryGrid
import com.kevinschildhorn.fotopresenter.ui.compose.directory.FolderDirectoryItem
import com.kevinschildhorn.fotopresenter.ui.compose.directory.PhotoDirectoryItem
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.fill.Browser

@Preview
@Composable
fun BaseDirectoryPreview() {
    Column {
        BaseDirectory() {}
    }
}

@Preview
@Composable
fun PhotoDirectoryPreview() {
    Column {
        PhotoDirectoryItem(
            imageVector = EvaIcons.Fill.Browser
        )
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
        listOf(
            MockNetworkDirectory("Test 1", id = 1),
            MockNetworkDirectory("Test 2", id = 2),
            MockNetworkDirectory("Test 3", id = 3),
            MockNetworkDirectory("Test 4", id = 4),
            MockNetworkDirectory("Test 6", id = 5),
            MockNetworkDirectory("Test 5", id = 6),
        )
    ) {

    }

}
