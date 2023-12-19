package com.kevinschildhorn.fotopresenter.ui.screens.directory.composables.navrail

import androidx.compose.material.Icon
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.kevinschildhorn.atomik.color.base.composeColor
import com.kevinschildhorn.fotopresenter.ui.atoms.FotoColors
import com.kevinschildhorn.fotopresenter.ui.screens.directory.DirectoryOverlay
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.fill.Menu
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.size

@Composable
fun NavigationRailMenuButton(
    onClick: () -> Unit
) {
    TextButton(
        modifier = Modifier.size(55.dp),
        onClick = onClick
    ) {
        Icon(
            EvaIcons.Fill.Menu,
            contentDescription = "Menu",
            tint = FotoColors.backgroundText.composeColor
        )
    }
}