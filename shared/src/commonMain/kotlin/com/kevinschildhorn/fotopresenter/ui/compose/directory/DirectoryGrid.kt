package com.kevinschildhorn.fotopresenter.ui.compose.directory

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import com.kevinschildhorn.fotopresenter.ui.compose.common.ActionSheet
import com.kevinschildhorn.fotopresenter.ui.state.ActionSheetAction
import com.kevinschildhorn.fotopresenter.ui.state.DirectoryGridCellState
import com.kevinschildhorn.fotopresenter.ui.state.DirectoryGridState
import com.kevinschildhorn.fotopresenter.ui.state.FolderDirectoryGridCellState
import com.kevinschildhorn.fotopresenter.ui.state.ImageDirectoryGridCellState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DirectoryGrid(
    directoryContent: DirectoryGridState,
    gridSize: Int = 5,
    modifier: Modifier = Modifier,
    onFolderPressed: (Int) -> Unit,
    onImageDirectoryPressed: (Int) -> Unit,
    onStartSlideshow: (Int) -> Unit,
) {
    var actionSheetVisible by remember { mutableStateOf(false) }
    var contextMenuPhotoState by rememberSaveable { mutableStateOf<DirectoryGridCellState?>(null) }
    val haptics = LocalHapticFeedback.current

    LazyVerticalGrid(
        columns = GridCells.Fixed(gridSize),
        modifier = modifier.zIndex(0f),
    ) {
        items(directoryContent.allStates, { it.id }) { state ->
            val directoryItemModifier =
                Modifier
                    .padding(5.dp)
                    .combinedClickable(
                        onClick = {
                            (state as? ImageDirectoryGridCellState)?.let { imageContent ->
                                onImageDirectoryPressed(imageContent.id)
                            } ?: run {
                                onFolderPressed(state.id)
                            }
                        },
                        onLongClick = {
                            haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                            contextMenuPhotoState = state
                            actionSheetVisible = true
                        },
                        onLongClickLabel = "Action Sheet",
                    )
            (state as? FolderDirectoryGridCellState)?.let { folderContent ->
                FolderDirectoryGridCell(
                    folderContent,
                    modifier = directoryItemModifier,
                )
            }
            (state as? ImageDirectoryGridCellState)?.let { imageContent ->
                ImageDirectoryGridCell(
                    imageContent,
                    modifier = directoryItemModifier,
                )
            }
        }
    }
    ActionSheet(
        visible = actionSheetVisible,
        offset = 200,
        values = contextMenuPhotoState?.actionSheetContexts ?: emptyList(),
        onClick = {
            when (it.action) {
                ActionSheetAction.START_SLIDESHOW -> {
                    onStartSlideshow(contextMenuPhotoState?.id!!)
                }
                ActionSheetAction.NONE -> {
                }
            }
            actionSheetVisible = false
            contextMenuPhotoState = null
        },
        onDismiss = {
            actionSheetVisible = false
            contextMenuPhotoState = null
        }
    )
}
