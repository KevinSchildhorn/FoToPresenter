package me.kevinschildhorn.atomik.atomic.atoms

import me.kevinschildhorn.atomik.atomic.atoms.interfaces.AtomType
import me.kevinschildhorn.atomik.atomic.atoms.interfaces.BorderedAtom
import me.kevinschildhorn.atomik.atomic.atoms.interfaces.TextAtom
import me.kevinschildhorn.atomik.color.base.AtomikColor
import me.kevinschildhorn.atomik.typography.base.AtomikTypography

class TextFieldAtom(
    override val borderColor: AtomikColor,
    override val textColor: AtomikColor,
    override val font: AtomikTypography,
) : Atom(), TextAtom, BorderedAtom {
    override val type: AtomType
        get() = AtomType.TEXTFIELD
}
