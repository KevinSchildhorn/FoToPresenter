package com.kevinschildhorn.fotopresenter.ui.screens.directory.composables.navbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kevinschildhorn.fotopresenter.ui.composables.FotoCheckbox
import com.kevinschildhorn.fotopresenter.ui.composables.FotoRadioButton
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.fill.Close

@Composable
fun DirectorySearchNavigationBar(
    tags: List<String>,
    allTags: Boolean,
    itemCount: Int,
    onClose: () -> Unit,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        LazyRow(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            item {
                SearchText("Tags: ", modifier = Modifier.padding(start = 10.dp))
            }
            items(tags) {
                SearchText(it)
            }
            item {
                SearchText("Count: $itemCount", modifier = Modifier.padding(start = 10.dp))
            }
            item {
                SearchText("Options: ", modifier = Modifier.padding(start = 10.dp))
            }
            item {
                FotoRadioButton(
                    title = if (allTags) "All of the Tags" else "Any of the Tags",
                    selected = true,
                    onRadioChanged = { },
                    enabled = false,
                    horizontalArrangement = Arrangement.Start,
                )
            }
            item {
                FotoCheckbox(
                    title = "Search Subfolders",
                    checked = true,
                    onCheckedChange = { },
                    enabled = false,
                    horizontalArrangement = Arrangement.Start,
                )
            }
        }
        Spacer(Modifier.weight(1f))
        IconButton(onClick = onClose) {
            Image(EvaIcons.Fill.Close, null)
        }
    }
}

@Composable
private fun SearchText(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(text, style = MaterialTheme.typography.subtitle1, modifier = modifier)
}
