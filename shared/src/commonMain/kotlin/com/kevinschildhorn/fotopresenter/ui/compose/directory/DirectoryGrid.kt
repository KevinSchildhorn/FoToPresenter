package com.kevinschildhorn.fotopresenter.ui.compose.directory

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.kevinschildhorn.fotopresenter.data.DirectoryContents
import com.kevinschildhorn.fotopresenter.data.FolderDirectoryContent
import com.kevinschildhorn.fotopresenter.data.ImageDirectoryContent
import com.kevinschildhorn.fotopresenter.data.network.NetworkDirectory
import com.kevinschildhorn.fotopresenter.ui.compose.common.ActionSheet

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DirectoryGrid(
    directoryContent: DirectoryContents,
    gridSize: Int = 5,
    modifier: Modifier = Modifier,
    onDirectoryPressed: (NetworkDirectory) -> Unit
) {
    var actionSheetVisible by remember { mutableStateOf(false) }
    var contextMenuPhotoId by rememberSaveable { mutableStateOf<Int?>(null) }
    val haptics = LocalHapticFeedback.current

    LazyVerticalGrid(
        columns = GridCells.Fixed(gridSize),
        modifier = modifier.zIndex(0f)
    ) {
        items(directoryContent.allDirectories, { it.directory.id }) { content ->

            val directoryItemModifier = Modifier
                .padding(5.dp)
                .combinedClickable(
                    onClick = {
                        onDirectoryPressed(content.directory)
                    },
                    onLongClick = {
                        haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                        contextMenuPhotoId = content.directory.id
                        actionSheetVisible = true
                    },
                    onLongClickLabel = "Action Sheet"
                )
            (content as? FolderDirectoryContent)?.let { folderContent ->
                FolderDirectoryItem(
                    folderContent.directory.name,
                    modifier = directoryItemModifier,
                )
            }
            (content as? ImageDirectoryContent)?.let { imageContent ->
                PhotoDirectoryItem(
                    imageContent,
                    modifier = directoryItemModifier,
                )

            }
        }
    }
    ActionSheet(
        visible = actionSheetVisible,
        offset = 200,
        values = listOf("Option A", "Option B")
    ) {
        actionSheetVisible = false
        contextMenuPhotoId = null
    }
}