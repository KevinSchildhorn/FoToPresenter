package com.kevinschildhorn.fotopresenter.ui.screens.directory.composables.search

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import com.kevinschildhorn.fotopresenter.ui.TestTags
import com.kevinschildhorn.fotopresenter.ui.atoms.Padding
import com.kevinschildhorn.fotopresenter.ui.atoms.fotoColors
import com.kevinschildhorn.fotopresenter.ui.testTag
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.fill.Search
import compose.icons.evaicons.fill.Close

@Composable
fun DirectorySearchBar(
    onSearch: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    var searchQuery by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        value = searchQuery,
        onValueChange = { 
            searchQuery = it
            onSearch(it)
        },
        modifier = modifier
            .testTag(TestTags.Directory.TopBar.SEARCH_BAR),
        placeholder = { Text("Search directories...") },
        leadingIcon = { 
            Icon(
                imageVector = EvaIcons.Fill.Search,
                contentDescription = "Search",
                tint = fotoColors.onSurface
            )
        },
        trailingIcon = {
            if (searchQuery.isNotEmpty()) {
                IconButton(
                    onClick = {
                        searchQuery = ""
                        onSearch("")
                        focusManager.clearFocus()
                    }
                ) {
                    Icon(
                        imageVector = EvaIcons.Fill.Close,
                        contentDescription = "Clear search",
                        tint = fotoColors.onSurface
                    )
                }
            }
        },
        singleLine = true,
        colors = androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors(
            textColor = fotoColors.onSurface,
            cursorColor = fotoColors.primary,
            focusedBorderColor = fotoColors.primary,
            unfocusedBorderColor = fotoColors.onSurface
        )
    )
} 