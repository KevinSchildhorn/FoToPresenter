package com.kevinschildhorn.fotopresenter.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.kevinschildhorn.fotopresenter.data.network.MockNetworkDirectory
import com.kevinschildhorn.fotopresenter.ui.compose.directory.BaseDirectory
import com.kevinschildhorn.fotopresenter.ui.compose.directory.DirectoryGrid
import com.kevinschildhorn.fotopresenter.ui.compose.directory.FolderDirectoryEmpty
import com.kevinschildhorn.fotopresenter.ui.compose.directory.PhotoDirectory
import com.kevinschildhorn.fotopresenter.ui.state.DirectoryContents
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
        PhotoDirectory(
            imageVector = EvaIcons.Fill.Browser
        )
    }
}

@Preview
@Composable
fun FolderDirectoryEmptyPreview() {
    Column {
        FolderDirectoryEmpty("Folder")
    }
}


@Preview
@Composable
fun DirectoryGridPreview() {
    DirectoryGrid(
        listOf(
            DirectoryContents(MockNetworkDirectory("Test 1", id = 1)),
            DirectoryContents(MockNetworkDirectory("Test 2", id = 2)),
            DirectoryContents(MockNetworkDirectory("Test 3", id = 3)),
            DirectoryContents(MockNetworkDirectory("Test 4", id = 4)),
            DirectoryContents(MockNetworkDirectory("Test 6", id = 5)),
            DirectoryContents(MockNetworkDirectory("Test 5", id = 6)),
        )
    ) {

    }

}
