package com.kevinschildhorn.fotopresenter.ui.screens.directory.composables.search

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import com.kevinschildhorn.fotopresenter.ui.TestTags
import com.kevinschildhorn.fotopresenter.ui.atoms.fotoColors
import com.kevinschildhorn.fotopresenter.ui.testTag
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.fill.Close
import compose.icons.evaicons.fill.Search

@Composable
fun DirectorySearchBar(
    searchText: String,
    onSearch: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        value = searchText,
        onValueChange = {
            onSearch(it)
        },
        modifier =
            modifier.testTag(TestTags.Directory.TopBar.SEARCH_BAR),
        placeholder = { Text("Search directories...") },
        leadingIcon = {
            Icon(
                imageVector = EvaIcons.Fill.Search,
                contentDescription = "Search",
                tint = fotoColors.onSurface,
            )
        },
        trailingIcon = {
            if (searchText.isNotEmpty()) {
                IconButton(
                    onClick = {
                        onSearch("")
                        focusManager.clearFocus()
                    },
                ) {
                    Icon(
                        imageVector = EvaIcons.Fill.Close,
                        contentDescription = "Clear search",
                        tint = fotoColors.onSurface,
                    )
                }
            }
        },
        singleLine = true,
        colors =
            androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors(
                textColor = fotoColors.onSurface,
                cursorColor = fotoColors.primary,
                focusedBorderColor = fotoColors.primary,
                unfocusedBorderColor = fotoColors.onSurface,
            ),
    )
}
