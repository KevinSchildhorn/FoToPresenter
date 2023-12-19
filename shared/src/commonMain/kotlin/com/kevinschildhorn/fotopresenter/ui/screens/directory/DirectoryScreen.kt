package com.kevinschildhorn.fotopresenter.ui.screens.directory

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kevinschildhorn.atomik.color.base.composeColor
import com.kevinschildhorn.fotopresenter.data.ImageSlideshowDetails
import com.kevinschildhorn.fotopresenter.ui.UiState
import com.kevinschildhorn.fotopresenter.ui.atoms.FotoColors
import com.kevinschildhorn.fotopresenter.ui.atoms.Padding
import com.kevinschildhorn.fotopresenter.ui.screens.common.ActionSheetAction
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.ActionSheet
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.ConfirmationDialog
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.ErrorView
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.ImagePreviewOverlay
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.LoadingOverlay
import com.kevinschildhorn.fotopresenter.ui.screens.directory.composables.grid.DirectoryGrid
import com.kevinschildhorn.fotopresenter.ui.screens.directory.composables.navbar.DirectoryNavigationBar
import com.kevinschildhorn.fotopresenter.ui.screens.directory.composables.navrail.NavigationRailMenuButton
import com.kevinschildhorn.fotopresenter.ui.screens.directory.composables.navrail.NavigationRailOverlay
import com.kevinschildhorn.fotopresenter.ui.screens.playlist.PlaylistScreen
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.fill.Menu

enum class DirectoryOverlay {
    ACTION_SHEET,
    IMAGE,
    NAV_RAIL,
    LOGOUT_CONFIRMATION,
    PLAYLIST,
    NONE,
}

@Composable
fun DirectoryScreen(
    viewModel: DirectoryViewModel,
    onLogout: () -> Unit,
    onStartSlideshow: (ImageSlideshowDetails) -> Unit,
    onShowPlaylists: () -> Unit,
) {
    LaunchedEffect(Unit) {
        viewModel.refreshScreen()
    }
    val uiState by viewModel.uiState.collectAsState()
    val imageUiState by viewModel.imageUiState.collectAsState()
    var overlayVisible by remember { mutableStateOf(DirectoryOverlay.NONE) }
    var contextMenuPhotoState by rememberSaveable { mutableStateOf<DirectoryGridCellState?>(null) }

    // Navigation
    if (!uiState.loggedIn) onLogout()
    uiState.slideshowDetails?.let {
        onStartSlideshow(it)
    }

    //region UI
    Column {
        NavigationRailMenuButton {
            overlayVisible = DirectoryOverlay.NAV_RAIL
        }
        (uiState.state as? UiState.ERROR)?.let {
            ErrorView(
                it.message,
                modifier = Modifier.padding(
                    horizontal = Padding.STANDARD.dp,
                    vertical = Padding.SMALL.dp,
                )
            )
        }
        DirectoryNavigationBar(
            directories = uiState.currentPathList,
            onHome = {
                viewModel.navigateToFolder(-1)
            },
            onItem = {
                viewModel.navigateToFolder(it)
            },
            modifier = Modifier.padding(Padding.SMALL.dp)
        )
        DirectoryGrid(
            uiState.directoryGridState,
            onFolderPressed = {
                viewModel.changeDirectory(it)
            },
            onImageDirectoryPressed = {
                viewModel.setSelectedImageById(it)
                overlayVisible = DirectoryOverlay.IMAGE
            },
            onActionSheet = {
                contextMenuPhotoState = it
                overlayVisible = DirectoryOverlay.ACTION_SHEET
            },
        )
    }
    //endregion

    // Overlays

    //region ActionSheet
    ActionSheet(
        visible = overlayVisible == DirectoryOverlay.ACTION_SHEET,
        offset = 200,
        values = contextMenuPhotoState?.actionSheetContexts ?: emptyList(),
        onClick = {
            when (it.action) {
                ActionSheetAction.START_SLIDESHOW -> {
                    viewModel.startSlideshow(contextMenuPhotoState?.id!!)
                    overlayVisible = DirectoryOverlay.NONE
                    contextMenuPhotoState = null
                }

                ActionSheetAction.ADD_STATIC_LOCATION ->
                    overlayVisible = DirectoryOverlay.PLAYLIST

                ActionSheetAction.NONE -> {
                    overlayVisible = DirectoryOverlay.NONE
                    contextMenuPhotoState = null
                }
            }
        },
        onDismiss = {
            overlayVisible = DirectoryOverlay.NONE
            contextMenuPhotoState = null
        },
    )
    //endregion

    //region Selected Image
    imageUiState.selectedImage?.let {
        ImagePreviewOverlay(
            it,
            visible = overlayVisible == DirectoryOverlay.IMAGE,
            onDismiss = {
                viewModel.clearPresentedImage()
                overlayVisible = DirectoryOverlay.NONE
            },
            onBack = {
                viewModel.showPreviousImage()
            },
            onForward = {
                viewModel.showNextImage()
            },
        )
    }
    //endregion

    //region Loading
    if (uiState.state is UiState.LOADING) {
        LoadingOverlay()
    }
    //endregion

    //region NavigationRail
    NavigationRailOverlay(
        visible = overlayVisible == DirectoryOverlay.NAV_RAIL,
        onDismiss = {
            overlayVisible = DirectoryOverlay.NONE
        },
        onLogout = {
            overlayVisible = DirectoryOverlay.LOGOUT_CONFIRMATION
        },
        onPlaylists = onShowPlaylists,
    )
    //endregion

    //region Logout
    if (overlayVisible == DirectoryOverlay.LOGOUT_CONFIRMATION) {
        ConfirmationDialog(
            "Log Out",
            "Are you sure you want to logout?",
            onDismissRequest = {
                overlayVisible = DirectoryOverlay.NONE
            },
            onConfirmation = {
                viewModel.logout()
                overlayVisible = DirectoryOverlay.NONE
            },
        )
    }
    //endregion

    //region Playlist
    if (overlayVisible == DirectoryOverlay.PLAYLIST) {
        PlaylistScreen(viewModel) { playlist ->
            viewModel.addToPlaylist(contextMenuPhotoState, playlist)
            overlayVisible = DirectoryOverlay.NONE
            contextMenuPhotoState = null
        }
    }
    //endregion
}
