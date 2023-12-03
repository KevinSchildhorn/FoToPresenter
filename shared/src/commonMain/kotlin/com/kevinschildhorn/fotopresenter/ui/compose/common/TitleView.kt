package com.kevinschildhorn.fotopresenter.ui.compose.common

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kevinschildhorn.atomik.atomic.atoms.interfaces.TextAtom
import com.kevinschildhorn.atomik.atomic.atoms.textStyle
import com.kevinschildhorn.atomik.color.base.composeColor
import com.kevinschildhorn.fotopresenter.ui.atoms.FotoTypography
import com.kevinschildhorn.fotopresenter.ui.atoms.LoginScreenAtoms.title

@Composable
fun TitleView(
    value: String,
    modifier: Modifier = Modifier,
) {
    val atom = title(typography = FotoTypography.h3)

    Text(
        text = value,
        color = atom.textColor.composeColor,
        style = atom.textStyle,
        modifier = modifier,
    )

}