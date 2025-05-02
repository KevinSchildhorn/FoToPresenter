package com.kevinschildhorn.fotopresenter.ui.screens.directory

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import co.touchlab.kermit.Logger
import com.kevinschildhorn.fotopresenter.data.ImageSlideshowDetails
import com.kevinschildhorn.fotopresenter.ui.TestTags
import com.kevinschildhorn.fotopresenter.ui.UiState
import com.kevinschildhorn.fotopresenter.ui.atoms.Padding
import com.kevinschildhorn.fotopresenter.ui.screens.common.ActionSheetAction
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.AdvancedSearchDialog
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.ConfirmationDialog
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.ErrorView
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.ImagePreviewOverlay
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.LoadingOverlay
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.SortDialog
import com.kevinschildhorn.fotopresenter.ui.screens.directory.composables.grid.DirectoryGrid
import com.kevinschildhorn.fotopresenter.ui.screens.directory.composables.navbar.DirectoryNavigationBar
import com.kevinschildhorn.fotopresenter.ui.screens.directory.composables.navrail.AppNavigationRail
import com.kevinschildhorn.fotopresenter.ui.testTag
import kotlinx.coroutines.launch

enum class DirectoryOverlayType {
    ACTION_SHEET,
    IMAGE,
    LOGOUT_CONFIRMATION,
    SORT,
    ADVANCED_SEARCH,
    DIRECTORY_ACTION_SHEET,
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

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current

    uiState.slideshowDetails?.let {
        viewModel.clearOverlay()
        onStartSlideshow(it)
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            DirectoryTopBar(
                searchText = uiState.searchText,
                showMenu = { scope.launch { scaffoldState.drawerState.open() } },
                onSearchChanged = { viewModel.onSearch(it) },
                showOverlay = { viewModel.showOverlay(it) },
            )
        },
        drawerContent = { // TODO: Too wide
            AppNavigationRail(
                onLogout = {
                    scope.launch {
                        scaffoldState.drawerState.close()
                    }
                    viewModel.showOverlay(DirectoryOverlayType.LOGOUT_CONFIRMATION)
                },
                onPlaylists = onShowPlaylists,
            )
        },
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
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
                modifier =
                    Modifier.padding(Padding.SMALL.dp)
                        .testTag(TestTags.Directory.NAVIGATION_BAR),
            )
            // Grid
            DirectoryGrid(
                uiState.directoryGridUIState,
                onFolderPressed = { folderId ->
                    focusManager.clearFocus()
                    viewModel.navigateIntoDirectory(folderId)
                },
                onImageDirectoryPressed = { imageDirectoryId ->
                    focusManager.clearFocus()
                    viewModel.setSelectedImageById(imageDirectoryId)
                    viewModel.showOverlay(DirectoryOverlayType.IMAGE)
                },
                onActionSheet = { directoryUiState ->
                    focusManager.clearFocus()
                    viewModel.setSelectedDirectory(directoryUiState)
                    viewModel.showOverlay(DirectoryOverlayType.ACTION_SHEET)
                },
            )
        }

        // Overlays
        when (val selectionState = uiState.overlayUiState) {
            DirectoryOverlayUiState.None -> {
                Logger.i("KEVINS - None")
            }
            is DirectoryOverlayUiState.ImagePreview -> {
                Logger.i("KEVINS - ShowImagePreview")
                ImagePreviewOverlay(
                    selectionState.imageDirectory,
                    visible = true,
                    onDismiss = { viewModel.clearPresentedImage() },
                    onBack = { viewModel.showPreviousImage() },
                    onForward = { viewModel.showNextImage() },
                )
            }

            is DirectoryOverlayUiState.Actions -> {
                Logger.i("KEVINS - ACTIONS")
                DirectoryActionsOverlay(
                    selectionState,
                    onAction = {
                        when (it) {
                            ActionSheetAction.START_SLIDESHOW ->
                                viewModel.startSlideShow(
                                    selectionState.directory,
                                    withSubPhotos = false,
                                )

                            ActionSheetAction.START_SLIDESHOW_WITH_SUBFOLDERS ->
                                viewModel.startSlideShow(
                                    selectionState.directory,
                                    withSubPhotos = true,
                                )

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
                    onDismiss = { viewModel.clearOverlay() },
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
                        viewModel.clearOverlay()
                    },
                )
            }

            is DirectoryOverlayUiState.Sort -> {
                Logger.i("KEVINS - Rendering Sort Dialog")
                SortDialog(
                    "Sort Images by",
                    onDismissRequest = { viewModel.clearOverlay() },
                    onConfirmation = {
                        viewModel.setSortType(it)
                        viewModel.clearOverlay()
                    },
                )
            }
            is DirectoryOverlayUiState.AdvancedSearch -> {
                Logger.i("KEVINS - Advanced Search")
                AdvancedSearchDialog(
                    onDismissRequest = { viewModel.clearOverlay() },
                    onConfirmation = { tags, type, recursive ->
                        viewModel.setAdvancedSearch(tags, type, recursive)
                        viewModel.clearOverlay()
                    }
                )
            }
        }
        //endregion

        if (uiState.state is UiState.LOADING) {
            LoadingOverlay()
        }
    }
}
