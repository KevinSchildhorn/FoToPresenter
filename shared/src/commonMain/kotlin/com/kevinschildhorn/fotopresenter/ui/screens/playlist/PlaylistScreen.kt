package com.kevinschildhorn.fotopresenter.ui.screens.playlist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.kevinschildhorn.fotopresenter.data.PlaylistDetails
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.ConfirmationDialog
import com.kevinschildhorn.fotopresenter.ui.screens.playlist.composables.PlaylistOverlay
import com.kevinschildhorn.fotopresenter.ui.screens.playlist.composables.TextEntryDialog
import com.kevinschildhorn.fotopresenter.ui.screens.playlist.composables.TextListDialog

enum class PlaylistDialog {
    NONE,
    CREATE,
    DELETE,
    DETAILS,
    EDIT,
}

@Composable
fun PlaylistScreen(
    viewModel: PlaylistViewModel,
    overlaid: Boolean,
    onDismiss: (() -> Unit)? = null,
    onPlaylistSelected: (PlaylistDetails) -> Unit,
) {
    val uiState by viewModel.playlistState.collectAsState()
    var dialogOpen by remember { mutableStateOf(PlaylistDialog.NONE) }

    LaunchedEffect(Unit) {
        viewModel.refreshPlaylists()
    }

    PlaylistOverlay(
        uiState.playlists,
        overlaid = overlaid,
        onClick = { },
        onDetails = {
            dialogOpen = PlaylistDialog.DETAILS
            viewModel.setSelectedPlaylist(it)
        },
        onDelete = {
            dialogOpen = PlaylistDialog.DELETE
            viewModel.setSelectedPlaylist(it)
        },
        onEdit = {
            dialogOpen = PlaylistDialog.EDIT
            viewModel.setSelectedPlaylist(it)
        },
        onCreate = {
            dialogOpen = PlaylistDialog.CREATE
        },
        onDismiss = {
            if (onDismiss != null) {
                onDismiss()
            }
        },
        onPlay = { id, shuffle ->
            viewModel.getPlaylist(id)?.let {
                if (shuffle) {
                    onPlaylistSelected(it.copy(items = it.items.shuffled()))
                } else {
                    onPlaylistSelected(it)
                }
            }
        },
    )

    when (dialogOpen) {
        PlaylistDialog.CREATE -> {
            TextEntryDialog(
                title = "Create Playlist",
                initialValue = "",
                {
                    dialogOpen = PlaylistDialog.NONE
                },
                {
                    viewModel.createPlaylist(it)
                    dialogOpen = PlaylistDialog.NONE
                },
            )
        }

        PlaylistDialog.DELETE -> {
            ConfirmationDialog(
                "Delete Playlist",
                "Are you sure you want to delete this playlist?",
                onDismissRequest = {
                    dialogOpen = PlaylistDialog.NONE
                    viewModel.clearSelectedPlaylist()
                },
                onConfirmation = {
                    viewModel.deletePlaylist()
                    dialogOpen = PlaylistDialog.NONE
                    viewModel.clearSelectedPlaylist()
                },
            )
        }

        PlaylistDialog.DETAILS -> {
            uiState.selectedPlaylist?.let {
                TextListDialog(it.items.map { it.directoryPath.toString() }) {
                    dialogOpen = PlaylistDialog.NONE
                }
            }
        }

        else -> {
        }
    }
}
