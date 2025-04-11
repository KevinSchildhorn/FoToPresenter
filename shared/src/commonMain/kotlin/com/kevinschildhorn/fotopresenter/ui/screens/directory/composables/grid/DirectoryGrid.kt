package com.kevinschildhorn.fotopresenter.ui.screens.directory.composables.grid

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.isSecondaryPressed
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.kevinschildhorn.fotopresenter.ui.TestTags
import com.kevinschildhorn.fotopresenter.ui.screens.directory.DirectoryGridCellUIState
import com.kevinschildhorn.fotopresenter.ui.screens.directory.DirectoryGridUIState
import com.kevinschildhorn.fotopresenter.ui.testTag

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DirectoryGrid(
    directoryContent: DirectoryGridUIState,
    gridSize: Int = 5,
    modifier: Modifier = Modifier,
    onFolderPressed: (Long) -> Unit,
    onImageDirectoryPressed: (Long) -> Unit,
    onActionSheet: (DirectoryGridCellUIState) -> Unit,
) {
    val haptics = LocalHapticFeedback.current

    LazyVerticalGrid(
        columns = GridCells.Fixed(gridSize),
        modifier = modifier.zIndex(0f),
    ) {
        itemsIndexed(directoryContent.allStates, key = { _, item -> item.id }) { index, state ->
            val directoryItemModifier =
                Modifier
                    .testTag(TestTags.Directory.DIRECTORY(index, state.name))
                    .padding(5.dp)
                    .pointerInput(Unit) {
                        awaitPointerEventScope {
                            val event = awaitPointerEvent()
                            if (event.type == PointerEventType.Press &&
                                event.buttons.isSecondaryPressed
                            ) {
                                event.changes.forEach { e -> e.consume() }
                                onActionSheet(state)
                            }
                        }
                    }
                    .combinedClickable(
                        onClick = {
                            when (state) {
                                is DirectoryGridCellUIState.Folder -> onFolderPressed(state.id)
                                is DirectoryGridCellUIState.Image -> onImageDirectoryPressed(state.id)
                            }
                        },
                        onLongClick = {
                            haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                            onActionSheet(state)
                        },
                        onLongClickLabel = "Action Sheet",
                    )
            when (state) {
                is DirectoryGridCellUIState.Folder ->
                    FolderDirectoryGridCell(
                        state,
                        modifier = directoryItemModifier,
                    )

                is DirectoryGridCellUIState.Image ->
                    ImageDirectoryGridCell(
                        state,
                        modifier = directoryItemModifier,
                    )
            }
        }
    }
}
