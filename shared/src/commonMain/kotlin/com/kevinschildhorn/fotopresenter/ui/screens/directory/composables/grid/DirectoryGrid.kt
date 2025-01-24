package com.kevinschildhorn.fotopresenter.ui.screens.directory.composables.grid

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.isSecondaryPressed
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.kevinschildhorn.fotopresenter.ui.screens.directory.DirectoryGridCellState
import com.kevinschildhorn.fotopresenter.ui.screens.directory.DirectoryGridUIState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DirectoryGrid(
    directoryContent: DirectoryGridUIState,
    gridSize: Int = 5,
    modifier: Modifier = Modifier,
    onFolderPressed: (Long) -> Unit,
    onImageDirectoryPressed: (Long) -> Unit,
    onActionSheet: (DirectoryGridCellState) -> Unit,
) {
    val haptics = LocalHapticFeedback.current

    LazyVerticalGrid(
        columns = GridCells.Fixed(gridSize),
        modifier = modifier.zIndex(0f),
    ) {
        items(directoryContent.allStates, { it.id }) { state ->
            val directoryItemModifier =
                Modifier
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
                                is DirectoryGridCellState.Folder -> onFolderPressed(state.id)
                                is DirectoryGridCellState.Image -> onImageDirectoryPressed(state.id)
                            }
                        },
                        onLongClick = {
                            haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                            onActionSheet(state)
                        },
                        onLongClickLabel = "Action Sheet",
                    )
            when (state) {
                is DirectoryGridCellState.Folder ->
                    FolderDirectoryGridCell(
                        state,
                        modifier = directoryItemModifier,
                    )
                is DirectoryGridCellState.Image ->
                    ImageDirectoryGridCell(
                        state,
                        modifier = directoryItemModifier,
                    )
            }
        }
    }
}
