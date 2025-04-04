package com.kevinschildhorn.fotopresenter.ui.screens.directory

import androidx.compose.foundation.text.BasicText
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import com.kevinschildhorn.fotopresenter.ui.screens.directory.composables.navrail.DirectoryTitleBarButton
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.fill.Menu
import compose.icons.evaicons.fill.Options

@Composable
fun DirectoryTopBar(
    showMenu: () -> Unit,
    showOverlay: (DirectoryOverlayType) -> Unit,
) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.onBackground,
        navigationIcon = {
            DirectoryTitleBarButton(EvaIcons.Fill.Menu) {
                showMenu()
            }
        },
        actions = {
            DirectoryTitleBarButton(EvaIcons.Fill.Options) {
                showOverlay(DirectoryOverlayType.SORT)
            }
        },
        title = {
            BasicText("Directories")
        },
    )
}
