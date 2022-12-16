package me.kevinschildhorn.common.design.atomicLib.atoms

import me.kevinschildhorn.common.design.atomicLib.atoms.interfaces.AtomType
import me.kevinschildhorn.common.design.atomicLib.atoms.interfaces.ColorAtom
import me.kevinschildhorn.common.design.atomicLib.atoms.interfaces.EnablableAtom
import me.kevinschildhorn.common.design.atomicLib.atoms.interfaces.TextAtom
import me.kevinschildhorn.common.design.atomicLib.color.SharedColor
import me.kevinschildhorn.common.design.atomicLib.color.SharedEnabledColor
import me.kevinschildhorn.common.design.atomicLib.font.SharedTypography

class TextButtonAtom(
    override val textColor: SharedColor,
    override val font: SharedTypography,
    override val color: SharedColor,
    private val disabledColor: SharedColor,
) : Atom(), EnablableAtom, TextAtom, ColorAtom {

    override val type: AtomType = AtomType.BUTTON
    override val enabledColor: SharedEnabledColor
        get() = SharedEnabledColor(color, disabledColor)
}
