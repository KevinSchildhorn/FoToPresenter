package com.kevinschildhorn.fotopresenter.ui.screens.directory.composables.grid

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.material.MaterialTheme
import com.kevinschildhorn.fotopresenter.ui.composables.LoadingAsyncImage
import com.kevinschildhorn.fotopresenter.ui.screens.directory.DirectoryGridCellUIState

@Composable
fun ImageDirectoryGridCell(
    imageContent: DirectoryGridCellUIState.Image,
    modifier: Modifier = Modifier,
) {
    DirectoryGridCell(modifier) {
        LoadingAsyncImage(
            model = imageContent.directoryDetails.model,
            contentDescription = imageContent.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize().background(MaterialTheme.colors.surface),
        )
    }
}
