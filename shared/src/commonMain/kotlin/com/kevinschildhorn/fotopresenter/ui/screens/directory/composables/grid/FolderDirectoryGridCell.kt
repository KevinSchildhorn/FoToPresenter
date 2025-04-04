package com.kevinschildhorn.fotopresenter.ui.screens.directory.composables.grid

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kevinschildhorn.fotopresenter.ui.atoms.fotoColors
import com.kevinschildhorn.fotopresenter.ui.screens.directory.DirectoryGridCellUIState
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.fill.Folder

@Composable
fun FolderDirectoryGridCell(
    folderState: DirectoryGridCellUIState,
    modifier: Modifier = Modifier,
) {
    DirectoryGridCell(modifier) {
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(5.dp),
        ) {
            Icon(
                imageVector = EvaIcons.Fill.Folder,
                contentDescription = "Folder",
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.66f),
                tint = fotoColors.onSurface,
            )
            DirectoryGridCellText(
                folderState.name,
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )
        }
    }
}
