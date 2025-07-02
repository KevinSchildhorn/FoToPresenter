package com.kevinschildhorn.fotopresenter.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kevinschildhorn.fotopresenter.data.Path
import com.kevinschildhorn.fotopresenter.data.network.MockNetworkDirectoryDetails
import com.kevinschildhorn.fotopresenter.ui.atoms.FotoTypography
import com.kevinschildhorn.fotopresenter.ui.atoms.fotoColors
import com.kevinschildhorn.fotopresenter.ui.atoms.fotoShapes
import com.kevinschildhorn.fotopresenter.ui.screens.directory.DirectoryGridCellUIState
import com.kevinschildhorn.fotopresenter.ui.screens.directory.DirectoryGridUIState
import com.kevinschildhorn.fotopresenter.ui.screens.directory.DirectoryTopBar
import com.kevinschildhorn.fotopresenter.ui.screens.directory.composables.grid.DirectoryGrid
import com.kevinschildhorn.fotopresenter.ui.screens.directory.composables.grid.DirectoryGridCell
import com.kevinschildhorn.fotopresenter.ui.screens.directory.composables.grid.FolderDirectoryGridCell
import com.kevinschildhorn.fotopresenter.ui.screens.directory.composables.navbar.DirectoryNavigationBar
import com.kevinschildhorn.fotopresenter.ui.screens.directory.composables.navbar.DirectoryNavigationItem
import com.kevinschildhorn.fotopresenter.ui.screens.directory.composables.navbar.DirectorySearchNavigationBar
import com.kevinschildhorn.fotopresenter.ui.screens.directory.composables.overlay.AdvancedSearchDialog

@Preview
@Composable
fun BaseDirectoryPreview() {
    FotoTheme {
        PreviewBox {
            Column {
                DirectoryGridCell {}
            }
        }
    }
}

@Preview
@Composable
fun FolderDirectoryEmptyPreview() {
    FotoTheme {
        PreviewBox {
            Column {
                FolderDirectoryGridCell(DirectoryGridCellUIState.Folder("Hello", 0))
            }
        }
    }
}

@Preview
@Composable
fun DirectoryGridPreview() {
    FotoTheme {
        PreviewBox {
            DirectoryGrid(
                directoryContent =
                    DirectoryGridUIState(
                        currentPath = Path.EMPTY,
                        currentState = DirectoryGridCellUIState.Folder("Hello", 0),
                        folderStates =
                            listOf(
                                DirectoryGridCellUIState.Folder("Hello", 0),
                            ),
                        imageStates =
                            mutableListOf(
                                DirectoryGridCellUIState.Image(
                                    MockNetworkDirectoryDetails(),
                                    "Hello",
                                    1
                                ),
                            ),
                    ),
                onFolderPressed = {},
                onImageDirectoryPressed = {},
                onActionSheet = {},
            )
        }
    }
}

@Preview
@Composable
private fun DirectoryNavigationItemPreview() {
    FotoTheme {
        PreviewBox {
            DirectoryNavigationItem("Photos") {
            }
        }
    }
}

@Preview(device = "spec:width=1000dp,height=1080dp")
@Composable
private fun DirectoryNavigationBarPreview() {
    FotoTheme {
        PreviewBox {
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
    }
}

@Preview
@Composable
private fun SortDialogPreview() {
    FotoTheme {
        PreviewBox {
            AdvancedSearchDialog(
                onDismissRequest = {},
                onConfirmation = { _, _, _, _, _ -> },
            )
        }
    }
}

@Preview
@Composable
private fun DirectorySearchNavigationBarPreview() {
    FotoTheme {
        PreviewBox {
            DirectorySearchNavigationBar(
                tags = listOf("Tag1", "Tag2"),
                allTags = true,
                itemCount = 10,
            ) {}
        }
    }
}

@Preview(device = "spec:width=800dp,height=1080dp")
@Composable
private fun DirectoryTopBarPreview() {
    FotoTheme {
        PreviewBox {
            DirectoryTopBar("", {}, {}, {}, {})
        }
    }
}

@Composable
private fun FotoTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = fotoColors,
        typography = FotoTypography(),
        shapes = fotoShapes,
        content = content
    )
}

@Composable
private fun PreviewBox(content: @Composable (BoxScope.() -> Unit)) {
    Box(
        Modifier
            .background(MaterialTheme.colors.background)
            .padding(10.dp),
        content = content,
    )
}