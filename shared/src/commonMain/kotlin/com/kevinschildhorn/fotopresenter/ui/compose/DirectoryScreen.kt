package com.kevinschildhorn.fotopresenter.ui.compose

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.kevinschildhorn.fotopresenter.data.ImageDirectoryContent
import com.kevinschildhorn.fotopresenter.ui.atoms.Padding
import com.kevinschildhorn.fotopresenter.ui.compose.directory.DirectoryGrid
import com.kevinschildhorn.fotopresenter.ui.viewmodel.DirectoryViewModel

@Composable
fun DirectoryScreen(
    viewModel: DirectoryViewModel,
    onImageSelected: (ImageDirectoryContent) -> Unit,
) {
    LaunchedEffect(Unit) {
        viewModel.refreshScreen()
    }
    val uiState by viewModel.uiState.collectAsState()
    var imageState: ImageDirectoryContent? by remember { mutableStateOf(null) }
    uiState.state.asComposable(
        modifier =
        Modifier.padding(
            horizontal = Padding.STANDARD.dp,
            vertical = Padding.SMALL.dp,
        ),
    )
    DirectoryGrid(
        uiState.directoryContents,
        modifier =
        Modifier
            .padding(top = Padding.EXTRA_LARGE.dp),
    ) {
        it.asImageDirectory?.let {
            imageState = it
            onImageSelected(it)
        } ?: run {
            viewModel.changeDirectory(it.directory)
        }
    }
    imageState?.let {
        ImagePreviewOverlay(it){
            imageState = null
        }
    }
}
