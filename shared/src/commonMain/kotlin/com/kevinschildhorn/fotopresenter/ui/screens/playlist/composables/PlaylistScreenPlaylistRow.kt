package com.kevinschildhorn.fotopresenter.ui.screens.playlist.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kevinschildhorn.fotopresenter.ui.atoms.fotoColors
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.DialogButtonText
import compose.icons.EvaIcons
import compose.icons.evaicons.Outline
import compose.icons.evaicons.outline.Edit
import compose.icons.evaicons.outline.Info
import compose.icons.evaicons.outline.Trash

@Composable
fun PlaylistScreenPlaylistRow(
    title: String,
    onClick: () -> Unit,
    onDetails: () -> Unit,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth().height(55.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TextButton(
            onClick = onClick,
            modifier = Modifier.fillMaxSize(0.7f)
        ) {
            DialogButtonText(title)
            Spacer(Modifier.fillMaxWidth())
        }
        Row(modifier = Modifier.fillMaxHeight()) {
            TextButton(
                modifier = Modifier.width(44.dp),
                onClick = onDetails
            ) {
                Icon(
                    EvaIcons.Outline.Info,
                    tint = fotoColors.onSecondary,
                    contentDescription = "Details",
                )
            }
            TextButton(
                modifier = Modifier.width(44.dp),
                onClick = onEdit
            ) {
                Icon(
                    EvaIcons.Outline.Edit,
                    tint = fotoColors.onSecondary,
                    contentDescription = "Edit",
                )
            }
            TextButton(
                modifier = Modifier.width(44.dp),
                onClick = onDelete,
            ) {
                Icon(
                    EvaIcons.Outline.Trash,
                    tint = fotoColors.onSecondary,
                    contentDescription = "Trash"
                )
            }
        }
    }
}