package me.kevinschildhorn.common.design.atomicLib.atoms

import me.kevinschildhorn.common.design.atomicLib.atoms.interfaces.AtomType
import me.kevinschildhorn.common.design.atomicLib.atoms.interfaces.TextAtom
import me.kevinschildhorn.common.design.atomicLib.color.SharedColor
import me.kevinschildhorn.common.design.atomicLib.font.SharedTypography

class TextViewAtom(
    override val textColor: SharedColor,
    override val textSize: Double,
    override val font: SharedTypography,
) : Atom(), TextAtom {
    override val type: AtomType
        get() = AtomType.TEXT
}
