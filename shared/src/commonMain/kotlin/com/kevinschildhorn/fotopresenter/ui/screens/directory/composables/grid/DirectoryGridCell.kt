package com.kevinschildhorn.fotopresenter.ui.screens.directory.composables.grid

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kevinschildhorn.atomik.atomic.atoms.compose.textStyle
import com.kevinschildhorn.atomik.color.base.composeColor
import com.kevinschildhorn.fotopresenter.ui.atoms.FotoColors
import com.kevinschildhorn.fotopresenter.ui.screens.directory.DirectoryAtoms

@Composable
fun DirectoryGridCell(
    modifier: Modifier = Modifier,
    backgroundColor: Color = FotoColors.surface.composeColor,
    content: @Composable BoxScope.() -> Unit,
) {
    Box(
        modifier =
            modifier
                .clip(shape = RoundedCornerShape(5.dp))
                .aspectRatio(1f)
                .background(color = backgroundColor),
        content = content,
    )
}

@Composable
fun DirectoryGridCellText(
    text: String,
    modifier: Modifier = Modifier,
) {
    val molecule: DirectoryAtoms.EmptyPhotoMolecule = DirectoryAtoms.emptyDirectory
    Text(
        text = text,
        style = molecule.textAtom.textStyle,
        textAlign = TextAlign.Center,
        modifier =
            modifier
                .fillMaxSize(),
    )
}
