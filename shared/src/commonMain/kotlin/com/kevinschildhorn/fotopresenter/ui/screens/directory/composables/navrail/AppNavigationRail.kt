package com.kevinschildhorn.fotopresenter.ui.screens.directory.composables.navrail

import androidx.compose.material.Icon
import androidx.compose.material.NavigationRail
import androidx.compose.material.NavigationRailItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kevinschildhorn.fotopresenter.ui.TestTags
import androidx.compose.material.MaterialTheme
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
        backgroundColor = MaterialTheme.colors.surface,
        modifier = Modifier.testTag(TestTags.Directory.NavigationRail.NAVIGATION_RAIL),
    ) {
        NavigationRailItem(
            modifier = Modifier.testTag(TestTags.Directory.NavigationRail.ITEM_PLAYLIST),
            label = {
                Text("Playlists", color = MaterialTheme.colors.onSurface)
            },
            icon = {
                Icon(
                    EvaIcons.Outline.Monitor,
                    contentDescription = "Playlists",
                    tint = MaterialTheme.colors.onSurface,
                )
            },
            selected = true,
            onClick = onPlaylists,
        )
        NavigationRailItem(
            modifier = Modifier.testTag(TestTags.Directory.NavigationRail.ITEM_LOGOUT),
            label = {
                Text("Logout", color = MaterialTheme.colors.onSurface)
            },
            icon = {
                Icon(
                    EvaIcons.Fill.LogOut,
                    contentDescription = "Logout",
                    tint = MaterialTheme.colors.onSurface,
                )
            },
            selected = true,
            onClick = onLogout,
        )
    }
}
