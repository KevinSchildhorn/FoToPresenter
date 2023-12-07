package com.kevinschildhorn.fotopresenter.ui.compose

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.kevinschildhorn.fotopresenter.data.State
import com.kevinschildhorn.fotopresenter.ui.atoms.Padding
import com.kevinschildhorn.fotopresenter.ui.compose.directory.DirectoryGrid
import com.kevinschildhorn.fotopresenter.ui.viewmodel.DirectoryViewModel

@Composable
fun DirectoryScreen(viewModel: DirectoryViewModel) {
    LaunchedEffect(Unit) {
        viewModel.refreshScreen()
    }
    val uiState by viewModel.uiState.collectAsState()

    uiState.state.asComposable(
        modifier =
            Modifier.padding(
                horizontal = Padding.STANDARD.dp,
                vertical = Padding.SMALL.dp,
            ),
    )
    DirectoryGrid(
        uiState.directoryGridState,
        modifier =
            Modifier
                .padding(top = Padding.EXTRA_LARGE.dp),
        onFolderPressed = {
            uiState.selectedImage = State.IDLE
            viewModel.changeDirectory(it)
        },
        onImageDirectoryPressed = {
            uiState.selectedImage = it
        },
    )
    if (uiState.selectedImage != State.IDLE) {
        ImagePreviewOverlay(uiState.selectedImage) {
            uiState.selectedImage = State.IDLE
        }
    }
}
