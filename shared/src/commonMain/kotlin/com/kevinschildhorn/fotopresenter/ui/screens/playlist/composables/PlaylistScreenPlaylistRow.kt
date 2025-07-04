package com.kevinschildhorn.fotopresenter.ui.screens.playlist.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.DialogButtonText
import compose.icons.EvaIcons
import compose.icons.evaicons.Outline
import compose.icons.evaicons.outline.Edit
import compose.icons.evaicons.outline.Info
import compose.icons.evaicons.outline.PlayCircle
import compose.icons.evaicons.outline.Shuffle
import compose.icons.evaicons.outline.Trash

@Composable
fun PlaylistScreenPlaylistRow(
    title: String,
    onClick: () -> Unit,
    onDetails: () -> Unit,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    onPlay: () -> Unit,
    onPlayShuffled: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth().height(55.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        TextButton(onClick = onClick, modifier = Modifier.weight(1f)) {
            DialogButtonText(title, modifier = Modifier.fillMaxWidth())
        }
        Row(modifier = Modifier.fillMaxHeight()) {
            TextButton(
                modifier = Modifier.width(44.dp),
                onClick = onDetails,
            ) {
                Icon(
                    EvaIcons.Outline.Info,
                    tint = MaterialTheme.colors.onSecondary,
                    contentDescription = "Details",
                )
            }
            TextButton(
                modifier = Modifier.width(44.dp),
                onClick = onEdit,
            ) {
                Icon(
                    EvaIcons.Outline.Edit,
                    tint = MaterialTheme.colors.onSecondary,
                    contentDescription = "Edit",
                )
            }
            TextButton(
                modifier = Modifier.width(44.dp),
                onClick = onDelete,
            ) {
                Icon(
                    EvaIcons.Outline.Trash,
                    tint = MaterialTheme.colors.onSecondary,
                    contentDescription = "Trash",
                )
            }
            Box(Modifier.width(44.dp))
            TextButton(
                modifier = Modifier.width(44.dp),
                onClick = onPlayShuffled,
            ) {
                Icon(
                    EvaIcons.Outline.Shuffle,
                    tint = MaterialTheme.colors.onSecondary,
                    contentDescription = "Shuffle",
                )
            }
            TextButton(
                modifier = Modifier.width(44.dp),
                onClick = onPlay,
            ) {
                Icon(
                    EvaIcons.Outline.PlayCircle,
                    tint = MaterialTheme.colors.onSecondary,
                    contentDescription = "Play",
                )
            }
        }
    }
}
