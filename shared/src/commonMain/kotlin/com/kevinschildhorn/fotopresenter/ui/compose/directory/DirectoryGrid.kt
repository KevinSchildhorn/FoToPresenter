package com.kevinschildhorn.fotopresenter.ui.compose.directory

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.kevinschildhorn.fotopresenter.data.network.NetworkDirectory
import com.kevinschildhorn.fotopresenter.ui.compose.common.ActionSheet

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DirectoryGrid(
    directories: List<NetworkDirectory>,
    gridSize: Int = 5,
) {
    var actionSheetVisible by remember { mutableStateOf(false) }
    var contextMenuPhotoId by rememberSaveable { mutableStateOf<Int?>(null) }
    val haptics = LocalHapticFeedback.current

    LazyVerticalGrid(
        columns = GridCells.Fixed(gridSize),
        modifier = Modifier.background(Color.Blue).zIndex(0f)
    ) {
        items(directories, { it.id }) {
            FolderDirectoryEmpty(
                it.name,
                modifier = Modifier
                    .padding(5.dp)
                    .combinedClickable(
                        onClick = { },
                        onLongClick = {
                            haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                            contextMenuPhotoId = it.id
                            actionSheetVisible = true
                        },
                        onLongClickLabel = "Hello"
                    ),
            )
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