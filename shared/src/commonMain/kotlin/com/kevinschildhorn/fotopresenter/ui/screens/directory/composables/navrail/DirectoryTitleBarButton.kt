package com.kevinschildhorn.fotopresenter.ui.screens.directory.composables.navrail

import androidx.compose.material.Icon
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.kevinschildhorn.atomik.color.base.composeColor
import com.kevinschildhorn.fotopresenter.ui.atoms.FotoColors
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.size
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun DirectoryTitleBarButton(
    imageVector: ImageVector,
    onClick: () -> Unit
) {
    TextButton(
        modifier = Modifier.size(55.dp),
        onClick = onClick
    ) {
        Icon(
            imageVector,
            contentDescription = "Menu",
            tint = FotoColors.backgroundText.composeColor
        )
    }
}