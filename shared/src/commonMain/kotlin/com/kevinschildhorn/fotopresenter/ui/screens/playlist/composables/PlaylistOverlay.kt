package com.kevinschildhorn.fotopresenter.ui.screens.playlist.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kevinschildhorn.fotopresenter.Playlist
import com.kevinschildhorn.fotopresenter.data.PlaylistDetails
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.Overlay

@Composable
fun PlaylistOverlay(
    options: List<PlaylistDetails> = emptyList(),
    onCreate: () -> Unit,
    onClick: (Long) -> Unit,
    onDetails: (Long) -> Unit,
    onEdit: (Long) -> Unit,
    onDelete: (Long) -> Unit,
    onDismiss: () -> Unit,
) {
    Overlay(
        5f,
        visible = true,
        onDismiss = onDismiss,
    ) {
        PlaylistColumn(
            options,
            onCreate = onCreate,
            onClick = onClick,
            onDetails = onDetails,
            onEdit = onEdit,
            onDelete = onDelete,
        )
    }
}