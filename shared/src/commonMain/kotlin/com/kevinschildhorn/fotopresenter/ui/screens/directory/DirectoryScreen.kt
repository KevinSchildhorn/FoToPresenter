package com.kevinschildhorn.fotopresenter.ui.screens.directory

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.kevinschildhorn.fotopresenter.data.ImageSlideshowDetails
import com.kevinschildhorn.fotopresenter.ui.UiState
import com.kevinschildhorn.fotopresenter.ui.atoms.Padding
import com.kevinschildhorn.fotopresenter.ui.screens.common.ActionSheetAction
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.ActionSheet
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.ConfirmationDialog
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.ErrorView
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.ImagePreviewOverlay
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.LoadingOverlay
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.SortDialog
import com.kevinschildhorn.fotopresenter.ui.screens.directory.composables.grid.DirectoryGrid
import com.kevinschildhorn.fotopresenter.ui.screens.directory.composables.navbar.DirectoryNavigationBar
import com.kevinschildhorn.fotopresenter.ui.screens.directory.composables.navrail.AppNavigationRail
import kotlinx.coroutines.launch

enum class DirectoryOverlayType {
    ACTION_SHEET,
    IMAGE,
    LOGOUT_CONFIRMATION,
    SORT,
    NONE,
}

@Composable
fun DirectoryScreen(
    viewModel: DirectoryViewModelNew,
    onLogout: () -> Unit,
    onStartSlideshow: (ImageSlideshowDetails) -> Unit,
    onShowPlaylists: () -> Unit,
) {
    LaunchedEffect(Unit) {
        viewModel.refreshScreen()
    }
    val uiState by viewModel.uiState.collectAsState()
    var overlayVisible by remember { mutableStateOf(DirectoryOverlayType.NONE) }

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            DirectoryTopBar(
                showMenu = { scope.launch { scaffoldState.drawerState.open() } },
                showOverlay = { overlayVisible = it }
            )
        },
        drawerContent = { // TODO: Too wide
            AppNavigationRail(
                onLogout = { overlayVisible = DirectoryOverlayType.LOGOUT_CONFIRMATION },
                onPlaylists = onShowPlaylists,
            )
        }
    ) {
        // Content
        Column {
            // Error View
            (uiState.state as? UiState.ERROR)?.let {
                ErrorView(
                    it.message,
                    modifier =
                        Modifier.padding(
                            horizontal = Padding.STANDARD.dp,
                            vertical = Padding.SMALL.dp,
                        ),
                )
            }
            // Navigation Bar
            DirectoryNavigationBar(
                directories = uiState.currentPathList,
                onHome = { viewModel.navigateBackToDirectory(-1) },
                onItem = { viewModel.navigateBackToDirectory(it) },
                modifier = Modifier.padding(Padding.SMALL.dp).testTag("DirectoryNavigationBar"),
            )
            // Grid
            DirectoryGrid(
                uiState.directoryGridUIState,
                onFolderPressed = { folderId -> viewModel.navigateIntoDirectory(folderId) },
                onImageDirectoryPressed = { imageDirectoryId ->
                    viewModel.setSelectedImageById(imageDirectoryId)
                    overlayVisible = DirectoryOverlayType.IMAGE
                },
                onActionSheet = { directoryUiState ->
                    viewModel.setSelectedDirectory(directoryUiState)
                    overlayVisible = DirectoryOverlayType.ACTION_SHEET
                },
            )
        }

        // Overlays
        when (val selectionState = uiState.overlayUiState) {
            DirectoryOverlayUiState.None -> {}
            is DirectoryOverlayUiState.ImagePreview -> {
                ImagePreviewOverlay(
                    selectionState.imageDirectory,
                    visible = true,
                    onDismiss = { viewModel.clearPresentedImage() },
                    onBack = { viewModel.showPreviousImage() },
                    onForward = { viewModel.showNextImage() },
                )
            }

            is DirectoryOverlayUiState.Actions -> {
                DirectoryActionsOverlay(
                    selectionState,
                    onAction = {
                        when (it) {
                            ActionSheetAction.START_SLIDESHOW -> viewModel.startSlideShow()
                            ActionSheetAction.ADD_STATIC_LOCATION ->
                                viewModel.addLocationToPlaylist(dynamic = false)

                            ActionSheetAction.SET_METADATA -> viewModel.startEditingMetadata()
                            ActionSheetAction.ADD_DYNAMIC_LOCATION ->
                                viewModel.addLocationToPlaylist(dynamic = true)

                            ActionSheetAction.NONE -> viewModel.clearOverlay()
                        }
                    },
                    onSaveMetadata = { viewModel.saveMetadata(it) },
                    changeOverlay = {},
                    onDismiss = { viewModel.clearOverlay() }
                )
            }

            is DirectoryOverlayUiState.LogoutConfirmation -> {
                ConfirmationDialog(
                    "Log Out",
                    "Are you sure you want to logout?",
                    onDismissRequest = { viewModel.clearOverlay() },
                    onConfirmation = {
                        viewModel.logout()
                        onLogout()
                        overlayVisible = DirectoryOverlayType.NONE
                    },
                )
            }
            is DirectoryOverlayUiState.Sort -> {
                SortDialog(
                    "Sort Images by",
                    onDismissRequest = { viewModel.clearOverlay() },
                    onConfirmation = {
                        viewModel.setSortType(it)
                        overlayVisible = DirectoryOverlayType.NONE
                    },
                )
            }
        }
        //endregion

        if (uiState.state is UiState.LOADING) {
            LoadingOverlay()
        }
    }
}
