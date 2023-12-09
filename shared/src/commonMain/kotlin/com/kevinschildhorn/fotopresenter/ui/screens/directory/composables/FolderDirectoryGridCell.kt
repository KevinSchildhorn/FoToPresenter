package com.kevinschildhorn.fotopresenter.ui.screens.directory.composables

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
import com.kevinschildhorn.atomik.color.base.composeColor
import com.kevinschildhorn.fotopresenter.ui.screens.directory.DirectoryAtoms
import com.kevinschildhorn.fotopresenter.ui.screens.directory.FolderDirectoryGridCellState

@Composable
fun FolderDirectoryGridCell(
    folderState: FolderDirectoryGridCellState,
    modifier: Modifier = Modifier,
) {
    val molecule: DirectoryAtoms.EmptyPhotoMolecule = DirectoryAtoms.emptyDirectory

    DirectoryGridCell(modifier) {
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(5.dp),
        ) {
            Icon(
                imageVector = molecule.imageAtom.image,
                contentDescription = "Folder",
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.66f),
                tint = molecule.imageAtom.color.composeColor,
            )
            DirectoryGridCellText(
                folderState.name,
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )
        }
    }
}
