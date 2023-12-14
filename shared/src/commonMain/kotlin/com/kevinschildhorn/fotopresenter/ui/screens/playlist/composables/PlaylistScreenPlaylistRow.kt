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
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kevinschildhorn.atomik.atomic.atoms.textStyle
import com.kevinschildhorn.atomik.color.base.composeColor
import com.kevinschildhorn.fotopresenter.ui.screens.playlist.PlaylistScreenAtoms
import compose.icons.EvaIcons
import compose.icons.evaicons.Outline
import compose.icons.evaicons.outline.Edit
import compose.icons.evaicons.outline.Trash

@Composable
fun PlaylistScreenPlaylistRow(
    title: String,
    onClick: () -> Unit,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
) {
    val atom = PlaylistScreenAtoms.rowTitle
    Row(
        modifier = Modifier.fillMaxWidth().height(55.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TextButton(
            onClick = onClick,
            modifier = Modifier.fillMaxSize(0.7f)
        ) {
            Text(
                text = title,
                color = atom.textColor.composeColor,
                style = atom.textStyle,
            )
            Spacer(Modifier.fillMaxWidth())
        }
        Row(modifier = Modifier.fillMaxHeight()) {
            TextButton(
                modifier = Modifier.width(44.dp),
                onClick = onEdit
            ) {
                Icon(
                    EvaIcons.Outline.Edit,
                    tint = atom.textColor.composeColor,
                    contentDescription = "Edit",
                )
            }
            TextButton(
                modifier = Modifier.width(44.dp),
                onClick = onDelete,
            ) {
                Icon(
                    EvaIcons.Outline.Trash,
                    tint = atom.textColor.composeColor,
                    contentDescription = "Trash"
                )
            }
        }
    }
}