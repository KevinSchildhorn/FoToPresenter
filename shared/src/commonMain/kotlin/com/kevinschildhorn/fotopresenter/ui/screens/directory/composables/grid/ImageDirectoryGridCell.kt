package com.kevinschildhorn.fotopresenter.ui.screens.directory.composables.grid

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import com.kevinschildhorn.fotopresenter.Res
import com.kevinschildhorn.fotopresenter.error
import com.kevinschildhorn.fotopresenter.photo_camera
import com.kevinschildhorn.fotopresenter.ui.atoms.fotoColors
import com.kevinschildhorn.fotopresenter.ui.screens.directory.DirectoryGridCellState
import org.jetbrains.compose.resources.painterResource

@Composable
fun ImageDirectoryGridCell(
    imageContent: DirectoryGridCellState.Image,
    modifier: Modifier = Modifier,
) {
    DirectoryGridCell(modifier) {
        AsyncImage(
            model = imageContent.directoryDetails,
            contentDescription = imageContent.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize().background(fotoColors.surface),
            error = painterResource(Res.drawable.error),
            placeholder = painterResource(Res.drawable.photo_camera),
        )
    }
}
