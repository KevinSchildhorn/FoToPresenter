package com.kevinschildhorn.fotopresenter.ui.screens.playlist


import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.ConfirmationDialog
import com.kevinschildhorn.fotopresenter.ui.screens.directory.DirectoryOverlay
import com.kevinschildhorn.fotopresenter.ui.screens.playlist.composables.PlaylistOverlay
import com.kevinschildhorn.fotopresenter.ui.screens.playlist.composables.TextEntryDialog

enum class PlaylistDialog{
    NONE,
    CREATE,
    DELETE,
    EDIT
}
@Composable
fun PlaylistScreen(
    viewModel: PlaylistViewModel,
    onLoginSuccess: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()
    var dialogOpen by remember { mutableStateOf(PlaylistDialog.NONE) }

    LaunchedEffect(Unit) {
        viewModel.refreshPlaylists()
    }

    PlaylistOverlay(
        uiState.playlists,
        onClick = {

        }, onDelete = {
            dialogOpen = PlaylistDialog.DELETE
            viewModel.setSelectedPlaylist(it)
        }, onEdit = {
            dialogOpen = PlaylistDialog.EDIT
            viewModel.setSelectedPlaylist(it)
        }, onCreate = {
            dialogOpen = PlaylistDialog.CREATE
        }
    )

    if (dialogOpen == PlaylistDialog.CREATE) {
        TextEntryDialog({
            dialogOpen = PlaylistDialog.NONE
        }, {
            viewModel.createPlaylist(it)
            dialogOpen = PlaylistDialog.NONE
        })
    }
    if (dialogOpen == PlaylistDialog.DELETE) {
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
}