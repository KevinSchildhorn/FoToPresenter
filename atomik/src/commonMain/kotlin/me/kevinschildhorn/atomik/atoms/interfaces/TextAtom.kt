package me.kevinschildhorn.atomik.atoms.interfaces

import me.kevinschildhorn.atomik.color.SharedColor
import me.kevinschildhorn.atomik.font.SharedTypography

interface TextAtom {
    val textColor: SharedColor
    val font: SharedTypography
}
