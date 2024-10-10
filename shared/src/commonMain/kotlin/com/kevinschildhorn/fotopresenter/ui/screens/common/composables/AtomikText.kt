package com.kevinschildhorn.fotopresenter.ui.screens.common.composables

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kevinschildhorn.atomik.atomic.atoms.interfaces.TextAtom
import com.kevinschildhorn.atomik.atomic.atoms.compose.textStyle
import com.kevinschildhorn.atomik.color.base.composeColor

@Composable
fun AtomikText(text: String, atom: TextAtom, modifier: Modifier = Modifier) {
    Text(
        text,
        style = atom.textStyle,
        color = atom.textColor.composeColor,
        modifier = modifier
    )
}