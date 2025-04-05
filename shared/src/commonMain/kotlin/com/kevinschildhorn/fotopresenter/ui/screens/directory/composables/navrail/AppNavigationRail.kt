package com.kevinschildhorn.fotopresenter.ui.screens.directory.composables.navrail

import androidx.compose.material.Icon
import androidx.compose.material.NavigationRail
import androidx.compose.material.NavigationRailItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kevinschildhorn.fotopresenter.ui.TestTags
import com.kevinschildhorn.fotopresenter.ui.atoms.fotoColors
import com.kevinschildhorn.fotopresenter.ui.testTag
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
        modifier = Modifier.testTag(TestTags.Directory.NAVIGATION_RAIL),
    ) {
        NavigationRailItem(
            modifier = Modifier.testTag(TestTags.Directory.NAVIGATION_RAIL_ITEM_PLAYLIST),
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
            modifier = Modifier.testTag(TestTags.Directory.NAVIGATION_RAIL_ITEM_LOGOUT),
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
