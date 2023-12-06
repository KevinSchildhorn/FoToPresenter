package com.kevinschildhorn.fotopresenter.ui.compose.directory

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import com.kevinschildhorn.atomik.color.base.composeColor
import com.kevinschildhorn.fotopresenter.ui.atoms.FotoColors


@Composable
fun PhotoDirectoryItem(
    imageBitmap: ImageBitmap,
    modifier: Modifier = Modifier,
) {
    BaseDirectory(
        backgroundColor = Color(0xD9D9D9),
        modifier = modifier,
    ){
        Image(
            bitmap = imageBitmap,
            contentDescription = "Folder",
            modifier = Modifier.fillMaxSize().background(FotoColors.surface.composeColor),
        )
    }
}

