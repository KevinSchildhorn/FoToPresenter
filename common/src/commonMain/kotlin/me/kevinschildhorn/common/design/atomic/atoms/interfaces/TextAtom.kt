package me.kevinschildhorn.common.design.atomic.atoms.interfaces

import me.kevinschildhorn.common.design.color.SharedColor
import me.kevinschildhorn.common.design.font.SharedFont

interface TextAtom {
    val textColor: SharedColor
    val textSize: Double
    val font: SharedFont
}
