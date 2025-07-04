package com.kevinschildhorn.fotopresenter.ui.screens.directory

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import co.touchlab.kermit.Logger
import com.kevinschildhorn.fotopresenter.data.ImageSlideshowDetails
import com.kevinschildhorn.fotopresenter.ui.TestTags
import com.kevinschildhorn.fotopresenter.ui.UiState
import com.kevinschildhorn.fotopresenter.ui.atoms.Padding
import com.kevinschildhorn.fotopresenter.ui.screens.common.ActionSheetAction
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.ConfirmationDialog
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.ErrorView
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.ImagePreviewOverlay
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.LoadingOverlay
import com.kevinschildhorn.fotopresenter.ui.screens.directory.composables.grid.DirectoryGrid
import com.kevinschildhorn.fotopresenter.ui.screens.directory.composables.navbar.DirectoryNavigationBar
import com.kevinschildhorn.fotopresenter.ui.screens.directory.composables.navbar.DirectorySearchNavigationBar
import com.kevinschildhorn.fotopresenter.ui.screens.directory.composables.navrail.AppNavigationRail
import com.kevinschildhorn.fotopresenter.ui.screens.directory.composables.overlay.AdvancedSearchDialog
import com.kevinschildhorn.fotopresenter.ui.screens.directory.composables.overlay.DirectoryOptionsOverlay
import com.kevinschildhorn.fotopresenter.ui.screens.directory.composables.overlay.SortDialog
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
    var gridSize by remember { mutableIntStateOf(5) }

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
                onGridSizeChanged = { increase ->
                    if (increase) {
                        gridSize += 1
                    } else {
                        gridSize -= 1
                    }
                },
                showOverlay = { viewModel.showOverlay(it) },
            )
        },
        drawerContent = {
            // TODO: Too wide
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
            when (val searchState = uiState.directoryAdvancedSearchUIState) {
                is DirectoryAdvancedSearchUIState.SUCCESS -> {
                    DirectorySearchNavigationBar(
                        tags = searchState.tags,
                        allTags = searchState.allTags,
                        itemCount = searchState.itemCount,
                    ) {
                        viewModel.clearSearch()
                    }
                }

                DirectoryAdvancedSearchUIState.IDLE -> {
                    DirectoryNavigationBar(
                        directories = uiState.currentPathList,
                        onHome = { viewModel.navigateBackToDirectory(-1) },
                        onItem = { viewModel.navigateBackToDirectory(it) },
                        modifier =
                            Modifier
                                .padding(Padding.SMALL.dp)
                                .testTag(TestTags.Directory.NAVIGATION_BAR),
                    )
                }

                else -> {}
            }
            // Grid
            DirectoryGrid(
                directoryContent = uiState.directoryGridUIState,
                gridSize = gridSize,
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
                            ActionSheetAction.START_SLIDESHOW ->
                                viewModel.showSlideshowOverlay()

                            ActionSheetAction.ADD_STATIC_LOCATION ->
                                viewModel.showPlaylistOverlay(dynamic = false)

                            ActionSheetAction.SET_METADATA -> viewModel.startEditingMetadata()
                            ActionSheetAction.ADD_DYNAMIC_LOCATION ->
                                viewModel.showPlaylistOverlay(dynamic = true)

                            ActionSheetAction.NONE -> viewModel.clearOverlay()
                            ActionSheetAction.ADD_ALL_LOCATION -> TODO()
                        }
                    },
                    onSaveMetadata = { viewModel.saveMetadata(it) },
                    onAddToPlaylist = { playlistId, directory ->
                        viewModel.addItemToPlaylist(playlistId, directory)
                    },
                    onShowSlideshow = { directory, subfolders, shuffleType ->
                        viewModel.startSlideShow(
                            directory = directory,
                            withSubPhotos = subfolders,
                            shuffleType = shuffleType,
                        )
                    },
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
                AdvancedSearchDialog(
                    onDismissRequest = { viewModel.clearOverlay() },
                    onConfirmation = { tags, type, recursive, startDate, endDate ->
                        viewModel.setAdvancedSearch(tags, type, recursive, startDate, endDate)
                        viewModel.clearOverlay()
                    },
                )
            }

            is DirectoryOverlayUiState.DirectoryOptions -> {
                DirectoryOptionsOverlay(
                    onDismiss = { viewModel.clearOverlay() },
                    onAction = { action ->
                        when (action) {
                            ActionSheetAction.START_SLIDESHOW -> {
                                viewModel.clearOverlay()
                                onStartSlideshow(ImageSlideshowDetails(viewModel.getAllImagesOnScreen()))
                            }

                            ActionSheetAction.ADD_ALL_LOCATION -> TODO()
                            else -> viewModel.clearOverlay()
                        }
                    },
                )
            }
        }
        //endregion

        if (uiState.state is UiState.LOADING ||
            uiState.directoryAdvancedSearchUIState == DirectoryAdvancedSearchUIState.LOADING
        ) {
            LoadingOverlay()
        }
    }
}
