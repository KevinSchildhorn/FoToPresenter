package me.kevinschildhorn.common.atomic.atoms.interfaces

import me.kevinschildhorn.common.color.SharedColor
import me.kevinschildhorn.common.font.SharedFont

interface TextAtom {
    val textColor: SharedColor
    val textSize: Double
    val font: SharedFont
}