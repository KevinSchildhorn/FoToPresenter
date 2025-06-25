package com.kevinschildhorn.fotopresenter.ui.screens.directory.composables.navrail

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun DirectoryTitleBarButton(
    imageVector: ImageVector,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    TextButton(
        modifier = modifier.size(55.dp),
        onClick = onClick,
    ) {
        Icon(
            imageVector,
            contentDescription = "Menu",
            tint = MaterialTheme.colors.onBackground,
        )
    }
}
