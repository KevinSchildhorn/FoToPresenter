package com.kevinschildhorn.fotopresenter.ui.compose

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.kevinschildhorn.fotopresenter.ui.atoms.Padding
import com.kevinschildhorn.fotopresenter.ui.compose.common.PrimaryButton
import com.kevinschildhorn.fotopresenter.ui.compose.directory.DirectoryGrid
import com.kevinschildhorn.fotopresenter.ui.viewmodel.DirectoryViewModel

@Composable
fun DirectoryScreen(
    viewModel: DirectoryViewModel,
    onLogout: () -> Unit,
    onStartSlideshow: (Int) -> Unit,
) {
    LaunchedEffect(Unit) {
        viewModel.refreshScreen()
    }
    val uiState by viewModel.uiState.collectAsState()

    if (!uiState.loggedIn) {
        onLogout()
    }
    uiState.state.asComposable(
        modifier =
        Modifier.padding(
            horizontal = Padding.STANDARD.dp,
            vertical = Padding.SMALL.dp,
        ),
    )
    PrimaryButton("Logout") {
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
    uiState.selectedImage?.let {
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
