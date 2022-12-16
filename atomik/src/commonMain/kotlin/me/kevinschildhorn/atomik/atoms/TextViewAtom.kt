package me.kevinschildhorn.atomik.atoms

import me.kevinschildhorn.atomik.atoms.interfaces.AtomType
import me.kevinschildhorn.atomik.atoms.interfaces.TextAtom
import me.kevinschildhorn.atomik.color.SharedColor
import me.kevinschildhorn.atomik.font.SharedTypography

class TextViewAtom(
    override val textColor: SharedColor,
    override val font: SharedTypography,
) : Atom(), TextAtom {
    override val type: AtomType
        get() = AtomType.TEXT
}
