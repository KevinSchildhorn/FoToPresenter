package com.kevinschildhorn.fotopresenter.ui.compose.directory

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
import com.kevinschildhorn.fotopresenter.ui.atoms.DirectoryAtoms

@Composable
fun FolderDirectoryItem(
    folderName: String,
    modifier: Modifier = Modifier,
) {
    val molecule: DirectoryAtoms.EmptyPhotoMolecule = DirectoryAtoms.emptyDirectory

    BaseDirectory(modifier) {
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
            DirectoryText(
                folderName,
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )
        }
    }
}
