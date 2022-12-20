package me.kevinschildhorn.atomik.atomic.atoms

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import me.kevinschildhorn.atomik.atomic.atoms.interfaces.TextAtom
import me.kevinschildhorn.atomik.typography.fontWeight

fun TextAtom.getTextStyle(fontFamily: FontFamily): TextStyle =
    TextStyle(
        fontFamily = fontFamily,
        fontWeight = this.typography.weight.fontWeight,
        fontSize = this.typography.size.sp,
        color = this.textColor.platformColor
    )
