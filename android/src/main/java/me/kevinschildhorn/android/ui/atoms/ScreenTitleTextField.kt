package me.kevinschildhorn.android.ui.atoms

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import me.kevinschildhorn.android.fontFamily
import me.kevinschildhorn.atomik.atomic.atoms.getTextStyle
import me.kevinschildhorn.common.design.theme.atomic.DesignAtoms

@Composable
fun ScreenTitleTextField(
    text: String
) {
    val atom = DesignAtoms.TextView.labelAtom
    val atomStyle = atom.getTextStyle(fontFamily)
    Text(
        text = text,
        style = atomStyle
    )
}
