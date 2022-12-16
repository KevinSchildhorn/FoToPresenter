package me.kevinschildhorn.common.design.atomicLib.atoms

import me.kevinschildhorn.common.design.atomicLib.atoms.interfaces.AtomType
import me.kevinschildhorn.common.design.atomicLib.atoms.interfaces.BorderedAtom
import me.kevinschildhorn.common.design.atomicLib.atoms.interfaces.TextAtom
import me.kevinschildhorn.common.design.atomicLib.color.SharedColor
import me.kevinschildhorn.common.design.atomicLib.font.SharedTypography

class TextFieldAtom(
    override val borderColor: SharedColor,
    override val textColor: SharedColor,
    override val font: SharedTypography,
) : Atom(), TextAtom, BorderedAtom {
    override val type: AtomType
        get() = AtomType.TEXTFIELD
}
