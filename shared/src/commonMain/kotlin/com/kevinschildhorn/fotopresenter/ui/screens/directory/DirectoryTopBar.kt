package com.kevinschildhorn.fotopresenter.ui.screens.directory

import androidx.compose.foundation.text.BasicText
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kevinschildhorn.fotopresenter.ui.TestTags
import com.kevinschildhorn.fotopresenter.ui.screens.directory.composables.navrail.DirectoryTitleBarButton
import com.kevinschildhorn.fotopresenter.ui.screens.directory.composables.search.DirectorySearchBar
import com.kevinschildhorn.fotopresenter.ui.testTag
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.fill.Menu
import compose.icons.evaicons.fill.Options

@Composable
fun DirectoryTopBar(
    searchText: String,
    showMenu: () -> Unit,
    onSearchChanged: (String) -> Unit,
    showOverlay: (DirectoryOverlayType) -> Unit,
) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.background,
        modifier = Modifier.testTag(TestTags.Directory.TopBar.TOP_BAR),
        contentColor = MaterialTheme.colors.onBackground,
        navigationIcon = {
            DirectoryTitleBarButton(EvaIcons.Fill.Menu, modifier = Modifier.testTag(TestTags.Directory.TopBar.MENU)) {
                showMenu()
            }
        },
        actions = {
            // Search Bar
            DirectorySearchBar(searchText, onSearch = onSearchChanged)
            DirectoryTitleBarButton(EvaIcons.Fill.Options, modifier = Modifier.testTag(TestTags.Directory.TopBar.OPTIONS)) {
                showOverlay(DirectoryOverlayType.SORT)
            }
        },
        title = {
            BasicText("Directories")
        },
    )
}
