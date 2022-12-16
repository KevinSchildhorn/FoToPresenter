package me.kevinschildhorn.atomik.atoms

import me.kevinschildhorn.atomik.atoms.interfaces.AtomType
import me.kevinschildhorn.atomik.atoms.interfaces.BorderedAtom
import me.kevinschildhorn.atomik.atoms.interfaces.TextAtom
import me.kevinschildhorn.atomik.color.SharedColor
import me.kevinschildhorn.atomik.font.SharedTypography

class TextFieldAtom(
    override val borderColor: SharedColor,
    override val textColor: SharedColor,
    override val font: SharedTypography,
) : Atom(), TextAtom, BorderedAtom {
    override val type: AtomType
        get() = AtomType.TEXTFIELD
}
