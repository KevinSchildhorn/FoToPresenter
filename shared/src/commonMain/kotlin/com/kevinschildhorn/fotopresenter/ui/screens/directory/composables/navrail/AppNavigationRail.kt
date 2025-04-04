package com.kevinschildhorn.fotopresenter.ui.screens.directory.composables.navrail

import androidx.compose.material.Icon
import androidx.compose.material.NavigationRail
import androidx.compose.material.NavigationRailItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.kevinschildhorn.fotopresenter.ui.atoms.fotoColors
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.Outline
import compose.icons.evaicons.fill.LogOut
import compose.icons.evaicons.outline.Monitor

@Composable
fun AppNavigationRail(
    onPlaylists: () -> Unit,
    onLogout: () -> Unit,
) {
    NavigationRail(
        backgroundColor = fotoColors.surface,
    ) {
        NavigationRailItem(
            label = {
                Text("Playlists", color = fotoColors.onSurface)
            },
            icon = {
                Icon(
                    EvaIcons.Outline.Monitor,
                    contentDescription = "Playlists",
                    tint = fotoColors.onSurface,
                )
            },
            selected = true,
            onClick = onPlaylists,
        )
        NavigationRailItem(
            label = {
                Text("Logout", color = fotoColors.onSurface)
            },
            icon = {
                Icon(
                    EvaIcons.Fill.LogOut,
                    contentDescription = "Logout",
                    tint = fotoColors.onSurface,
                )
            },
            selected = true,
            onClick = onLogout,
        )
    }
}
