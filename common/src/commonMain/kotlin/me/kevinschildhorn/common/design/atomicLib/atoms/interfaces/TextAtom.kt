package me.kevinschildhorn.common.design.atomicLib.atoms.interfaces

import me.kevinschildhorn.common.design.atomicLib.color.SharedColor
import me.kevinschildhorn.common.design.atomicLib.font.SharedTypography

interface TextAtom {
    val textColor: SharedColor
    val font: SharedTypography
}
