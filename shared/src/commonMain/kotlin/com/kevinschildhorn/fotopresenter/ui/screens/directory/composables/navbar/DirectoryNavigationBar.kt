package com.kevinschildhorn.fotopresenter.ui.screens.directory.composables.navbar

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kevinschildhorn.fotopresenter.data.Path
import com.kevinschildhorn.fotopresenter.ui.atoms.fotoColors
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.fill.ChevronRight

@Composable
fun DirectoryNavigationBar(
    directories: List<Path>,
    onHome: () -> Unit,
    onItem: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        DirectoryNavigationHome {
            onHome()
        }
        LazyRow {
            itemsIndexed(directories) { index, item ->
                Icon(
                    EvaIcons.Fill.ChevronRight,
                    tint = fotoColors.onSecondary,
                    contentDescription = null,
                    modifier = Modifier.height(44.dp),
                )
                DirectoryNavigationItem(item.toString()) {
                    onItem(index)
                }
            }
            items(directories) {
            }
        }
    }
}
