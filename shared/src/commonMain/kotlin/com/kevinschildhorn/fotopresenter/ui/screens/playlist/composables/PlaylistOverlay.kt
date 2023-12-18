package com.kevinschildhorn.fotopresenter.ui.screens.playlist.composables

import androidx.compose.runtime.Composable
import com.kevinschildhorn.fotopresenter.Playlist
import com.kevinschildhorn.fotopresenter.data.PlaylistDetails
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.Overlay

@Composable
fun PlaylistOverlay(
    options: List<PlaylistDetails> = emptyList(),
    onCreate: () -> Unit,
    onClick: (Long) -> Unit,
    onEdit: (Long) -> Unit,
    onDelete: (Long) -> Unit,
) {
    Overlay(
        5f,
        visible = true,
        onDismiss = {},
        ) {
        PlaylistColumn(options, onCreate, onClick, onEdit, onDelete)
    }
}