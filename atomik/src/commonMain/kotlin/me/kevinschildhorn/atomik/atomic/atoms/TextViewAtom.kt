package me.kevinschildhorn.atomik.atomic.atoms

import me.kevinschildhorn.atomik.atomic.atoms.interfaces.AtomType
import me.kevinschildhorn.atomik.atomic.atoms.interfaces.TextAtom
import me.kevinschildhorn.atomik.color.Color
import me.kevinschildhorn.atomik.typography.base.AtomikTypography

class TextViewAtom(
    override val textColor: Color,
    override val font: AtomikTypography,
) : Atom(), TextAtom {
    override val type: AtomType
        get() = AtomType.TEXT
}
