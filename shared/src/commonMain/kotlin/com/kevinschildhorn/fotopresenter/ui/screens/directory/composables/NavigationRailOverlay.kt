package com.kevinschildhorn.fotopresenter.ui.screens.directory.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.NavigationRail
import androidx.compose.material.NavigationRailItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.kevinschildhorn.atomik.color.base.composeColor
import com.kevinschildhorn.fotopresenter.ui.atoms.FotoColors
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.Overlay
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.fill.LogOut

@Composable
fun NavigationRailOverlay(
    visible: Boolean,
    onDismiss: () -> Unit,
    onLogout: () -> Unit,
) {
    Overlay(
        7f,
        visible = visible,
        onDismiss = onDismiss,
    ) {
        Row {
            NavigationRail {
                NavigationRailItem(
                    label = {
                        Text("Logout", color = FotoColors.surfaceText.composeColor)
                    },
                    icon = {
                        Icon(
                            EvaIcons.Fill.LogOut,
                            contentDescription = "Logout",
                            tint = FotoColors.surfaceText.composeColor
                        )
                    },
                    selected = true,
                    onClick = onLogout
                )
                NavigationRailItem(
                    label = {
                        Text("Logout", color = FotoColors.surfaceText.composeColor)
                    },
                    icon = {
                        Icon(
                            EvaIcons.Fill.LogOut,
                            contentDescription = "Logout",
                            tint = FotoColors.surfaceText.composeColor
                        )
                    },
                    selected = true,
                    onClick = onLogout
                )
            }
        }
    }
}