package com.kevinschildhorn.fotopresenter.ui.screens.directory

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.kevinschildhorn.atomik.atomic.atoms.textStyle
import com.kevinschildhorn.atomik.color.base.composeColor
import com.kevinschildhorn.fotopresenter.data.ImageSlideshowDetails
import com.kevinschildhorn.fotopresenter.ui.UiState
import com.kevinschildhorn.fotopresenter.ui.atoms.Padding
import com.kevinschildhorn.fotopresenter.ui.screens.common.ActionSheetAction
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.ActionSheet
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.ConfirmationDialog
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.ErrorView
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.FilterDialog
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.ImagePreviewOverlay
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.LoadingOverlay
import com.kevinschildhorn.fotopresenter.ui.screens.directory.DirectoryAtoms.ImageTicker
import com.kevinschildhorn.fotopresenter.ui.screens.directory.composables.grid.DirectoryGrid
import com.kevinschildhorn.fotopresenter.ui.screens.directory.composables.navbar.DirectoryNavigationBar
import com.kevinschildhorn.fotopresenter.ui.screens.directory.composables.navrail.DirectoryTitleBarButton
import com.kevinschildhorn.fotopresenter.ui.screens.directory.composables.navrail.NavigationRailOverlay
import com.kevinschildhorn.fotopresenter.ui.screens.playlist.PlaylistScreen
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.fill.Funnel
import compose.icons.evaicons.fill.Menu

enum class DirectoryOverlay {
    ACTION_SHEET,
    IMAGE,
    NAV_RAIL,
    LOGOUT_CONFIRMATION,
    PLAYLIST,
    FILTER,
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

    // Navigation
    if (!uiState.loggedIn) onLogout()
    uiState.slideshowDetails?.let {
        onStartSlideshow(it)
    }

    //region UI
    Column {
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            DirectoryTitleBarButton(EvaIcons.Fill.Menu) {
                overlayVisible = DirectoryOverlay.NAV_RAIL
            }
            DirectoryTitleBarButton(EvaIcons.Fill.Funnel) {
                overlayVisible = DirectoryOverlay.FILTER
            }
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
        Text(
            uiState.imageCountString,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = ImageTicker.textStyle,
            color = ImageTicker.textColor.composeColor
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
                viewModel.setSelectedDirectory(it)
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
        values = viewModel.actionSheetContexts,
        onClick = {
            when (it.action) {
                ActionSheetAction.START_SLIDESHOW -> {
                    viewModel.startSlideshow()
                    overlayVisible = DirectoryOverlay.NONE
                    viewModel.setSelectedDirectory(null)
                }

                ActionSheetAction.ADD_STATIC_LOCATION ->
                    overlayVisible = DirectoryOverlay.PLAYLIST

                ActionSheetAction.ADD_DYNAMIC_LOCATION ->
                    overlayVisible = DirectoryOverlay.PLAYLIST

                ActionSheetAction.NONE -> {
                    overlayVisible = DirectoryOverlay.NONE
                    viewModel.setSelectedDirectory(null)
                }
            }
        },
        onDismiss = {
            overlayVisible = DirectoryOverlay.NONE
            viewModel.setSelectedDirectory(null)
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

    if (overlayVisible == DirectoryOverlay.FILTER) {
        FilterDialog(
            "Filter Images by",
            onDismissRequest = {
                overlayVisible = DirectoryOverlay.NONE
            },
            onConfirmation = {
                viewModel.setFilterType(it)
            }
        )
    }

    //region Playlist
    if (overlayVisible == DirectoryOverlay.PLAYLIST) {
        PlaylistScreen(
            viewModel,
            overlaid = true,
            onDismiss = {
                overlayVisible = DirectoryOverlay.NONE
            }
        ) { playlist ->
            viewModel.addSelectedDirectoryToPlaylist(playlist)
            overlayVisible = DirectoryOverlay.NONE
            viewModel.setSelectedDirectory(null)
        }
    }
    //endregion
}
