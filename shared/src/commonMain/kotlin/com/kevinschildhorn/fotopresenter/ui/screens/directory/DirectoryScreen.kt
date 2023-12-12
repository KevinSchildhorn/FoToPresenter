package com.kevinschildhorn.fotopresenter.ui.screens.directory

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.kevinschildhorn.fotopresenter.data.ImageSlideshowDetails
import com.kevinschildhorn.fotopresenter.ui.atoms.Padding
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.ImagePreviewOverlay
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.PrimaryTextButton
import com.kevinschildhorn.fotopresenter.ui.screens.directory.composables.DirectoryGrid

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

    // Navigation
    if (!uiState.loggedIn) onLogout()
    uiState.slideshowDetails?.let {
        onStartSlideshow(it)
    }

    // State
    uiState.state.asComposable(
        modifier =
            Modifier.padding(
                horizontal = Padding.STANDARD.dp,
                vertical = Padding.SMALL.dp,
            ),
    )

    // UI
    PrimaryTextButton("Logout") {
        viewModel.logout()
    }
    DirectoryGrid(
        uiState.directoryGridState,
        modifier =
            Modifier
                .padding(top = Padding.EXTRA_LARGE.dp),
        onFolderPressed = {
            viewModel.changeDirectory(it)
        },
        onImageDirectoryPressed = {
            viewModel.setSelectedImageById(it)
        },
        onStartSlideshow = {
            viewModel.startSlideshow(it)
        },
    )
    imageUiState.selectedImage?.let {
        ImagePreviewOverlay(
            it,
            onDismiss = {
                viewModel.clearPresentedImage()
            },
            onBack = {
                viewModel.showPreviousImage()
            },
            onForward = {
                viewModel.showNextImage()
            },
        )
    }
}
