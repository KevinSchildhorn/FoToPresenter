package com.kevinschildhorn.fotopresenter.ui.screens.playlist.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kevinschildhorn.fotopresenter.ui.atoms.Padding
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kevinschildhorn.atomik.atomic.atoms.textStyle
import com.kevinschildhorn.atomik.color.base.composeColor
import com.kevinschildhorn.fotopresenter.ui.atoms.FotoColors
import com.kevinschildhorn.fotopresenter.ui.screens.playlist.PlaylistScreenAtoms

@Composable
fun PlaylistColumn(
    options: List<String> = emptyList(),
    onCreate: () -> Unit,
    onClick: (String) -> Unit,
    onEdit: (String) -> Unit,
    onDelete: (String) -> Unit,
) {
    val atom = PlaylistScreenAtoms.title
    Column(
        Modifier
            .fillMaxWidth()
            .padding(Padding.STANDARD.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(FotoColors.secondary.composeColor)
    ) {
        Column(Modifier.fillMaxWidth().padding(Padding.MEDIUM.dp)) {
            Text(
                "Playlists",
                style = atom.textStyle
            )
            LazyColumn {
                items(options) {
                    PlaylistScreenPlaylistRow(
                        title = it,
                        onClick = {
                            onClick(it)
                        },
                        onEdit = {
                            onEdit(it)
                        },
                        onDelete = {
                            onDelete(it)
                        }
                    )
                    Spacer(
                        Modifier.fillMaxWidth().height(1.dp)
                            .background(FotoColors.secondaryText.composeColor)
                    )
                }
            }
            PlaylistScreenCreateRow(onClick = onCreate)
        }
    }
}