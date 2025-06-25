package com.kevinschildhorn.fotopresenter.ui.screens.directory

import androidx.compose.runtime.Composable
import com.kevinschildhorn.fotopresenter.data.Directory
import com.kevinschildhorn.fotopresenter.ui.screens.common.ActionSheetAction
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.ActionSheet
import com.kevinschildhorn.fotopresenter.ui.screens.directory.composables.overlay.MetadataOverlay
import com.kevinschildhorn.fotopresenter.ui.screens.playlist.composables.PlaylistOverlay

/**
 * **DirectoryActionsOverlay**
 *
 * Handles all possible overlays regarding to actions for a directory.
 * This does *not* include things like logout and preview image.
 */
@Composable
fun DirectoryActionsOverlay(
    overlayState: DirectoryOverlayUiState.Actions,
    onAction: (ActionSheetAction) -> Unit,
    onSaveMetadata: (String) -> Unit,
    onAddToPlaylist: (Long, Directory) -> Unit,
    changeOverlay: (DirectoryOverlayType) -> Unit,
    onDismiss: () -> Unit,
) {
    when (overlayState) {
        is DirectoryOverlayUiState.Actions.Sheet -> {
            ActionSheet(
                visible = true,
                offset = 200,
                values = overlayState.directoryUiState.actionSheetContexts,
                onClick = { onAction(it.action) },
                onDismiss = onDismiss,
            )
        }

        is DirectoryOverlayUiState.Actions.EditMetaData -> {
            MetadataOverlay(
                initialValue = overlayState.metadata?.tagsString,
                onDismiss = onDismiss,
                onConfirmation = onSaveMetadata,
            )
        }

        is DirectoryOverlayUiState.Actions.AddToPlaylist -> {
            PlaylistOverlay(
                overlayState.playlists,
                overlaid = true,
                onClick = { id ->
                    onAddToPlaylist(id, overlayState.directory)
                },
                onDetails = { }, // TODO
                onDelete = { },
                onEdit = { },
                onCreate = { },
                onDismiss = onDismiss,
            )
        }
    }
}
