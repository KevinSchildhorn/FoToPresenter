package com.kevinschildhorn.fotopresenter.ui.screens.playlist.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kevinschildhorn.fotopresenter.data.PlaylistDetails
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.Overlay

@Composable
fun PlaylistOverlay(
    options: List<PlaylistDetails> = emptyList(),
    overlaid: Boolean,
    onCreate: () -> Unit,
    onClick: (Long) -> Unit,
    onPlay: (Long, Boolean) -> Unit,
    onDetails: (Long) -> Unit,
    onEdit: (Long) -> Unit,
    onDelete: (Long) -> Unit,
    onDismiss: () -> Unit,
) {
    if (overlaid) {
        Overlay(
            5f,
            visible = true,
            onDismiss = onDismiss,
        ) {
            Box(
                modifier = Modifier.align(Alignment.Center),
            ) {
                PlaylistColumn(
                    options,
                    onCreate = onCreate,
                    onClick = onClick,
                    onDetails = onDetails,
                    onEdit = onEdit,
                    onDelete = onDelete,
                    onPlay = onPlay,
                )
            }
        }
    } else {
        PlaylistColumn(
            options,
            modifier = Modifier.fillMaxHeight(),
            onCreate = onCreate,
            onClick = onClick,
            onDetails = onDetails,
            onEdit = onEdit,
            onDelete = onDelete,
            onPlay = onPlay,
        )
    }
}
