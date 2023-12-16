package com.kevinschildhorn.fotopresenter.ui.screens.directory

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.NavigationRail
import androidx.compose.material.NavigationRailItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.kevinschildhorn.atomik.color.base.composeColor
import com.kevinschildhorn.fotopresenter.data.ImageSlideshowDetails
import com.kevinschildhorn.fotopresenter.ui.UiState
import com.kevinschildhorn.fotopresenter.ui.atoms.FotoColors
import com.kevinschildhorn.fotopresenter.ui.atoms.Padding
import com.kevinschildhorn.fotopresenter.ui.screens.common.ActionSheetAction
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.ActionSheet
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.ErrorView
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.ImagePreviewOverlay
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.LoadingOverlay
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.Overlay
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.OverlayShadow
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.PrimaryTextButton
import com.kevinschildhorn.fotopresenter.ui.screens.directory.composables.DirectoryGrid
import com.kevinschildhorn.fotopresenter.ui.screens.directory.composables.DirectoryNavigationBar
import com.kevinschildhorn.fotopresenter.ui.screens.directory.composables.NavigationRailOverlay
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.fill.LogOut

enum class DirectoryOverlay {
    ACTION_SHEET,
    IMAGE,
    NAV_RAIL,
    NONE,
}

@Composable
fun DirectoryScreen(
    viewModel: DirectoryViewModel,
    onLogout: () -> Unit,
    onStartSlideshow: (ImageSlideshowDetails) -> Unit,
) {
    LaunchedEffect(Unit) {
        viewModel.refreshScreen()
    }
    val uiState by viewModel.uiState.collectAsState()
    val imageUiState by viewModel.imageUiState.collectAsState()
    var overlayVisible by remember { mutableStateOf(DirectoryOverlay.NAV_RAIL) }
    var contextMenuPhotoState by rememberSaveable { mutableStateOf<DirectoryGridCellState?>(null) }

    // Navigation
    if (!uiState.loggedIn) onLogout()
    uiState.slideshowDetails?.let {
        onStartSlideshow(it)
    }

    // UI
    Column {
        (uiState.state as? UiState.ERROR)?.let {
            ErrorView(
                it.message, modifier = Modifier.padding(
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
    ActionSheet(
        visible = overlayVisible == DirectoryOverlay.ACTION_SHEET,
        offset = 200,
        values = contextMenuPhotoState?.actionSheetContexts ?: emptyList(),
        onClick = {
            when (it.action) {
                ActionSheetAction.START_SLIDESHOW -> {
                    viewModel.startSlideshow(contextMenuPhotoState?.id!!)
                }

                ActionSheetAction.NONE -> {
                }
            }
            overlayVisible = DirectoryOverlay.NONE
            contextMenuPhotoState = null
        },
        onDismiss = {
            overlayVisible = DirectoryOverlay.NONE
            contextMenuPhotoState = null
        },
    )
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
    if (uiState.state is UiState.LOADING) {
        LoadingOverlay()
    }

    NavigationRailOverlay(
        visible = overlayVisible == DirectoryOverlay.NAV_RAIL,
        onDismiss = {
            overlayVisible = DirectoryOverlay.NONE
        },
        onLogout = {
            viewModel.logout()
        }
    )
}
