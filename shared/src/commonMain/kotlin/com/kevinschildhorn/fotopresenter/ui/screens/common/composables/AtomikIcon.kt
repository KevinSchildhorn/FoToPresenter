package com.kevinschildhorn.fotopresenter.ui.screens.common.composables

import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.kevinschildhorn.atomik.atomic.atoms.interfaces.TextAtom
import com.kevinschildhorn.atomik.color.base.composeColor

@Composable
fun AtomikIcon(
    imageVector: ImageVector,
    atom: TextAtom,
    modifier: Modifier = Modifier,
    contentDescription: String
) {
    Icon(
        imageVector = imageVector,
        contentDescription = contentDescription,
        tint = atom.textColor.composeColor,
        modifier = modifier,
    )
}