package me.kevinschildhorn.atomik.atomic.atoms

import me.kevinschildhorn.atomik.atomic.atoms.interfaces.AtomType
import me.kevinschildhorn.atomik.atomic.atoms.interfaces.TextAtom
import me.kevinschildhorn.atomik.color.base.AtomikColor
import me.kevinschildhorn.atomik.typography.base.AtomikFontFamily
import me.kevinschildhorn.atomik.typography.base.AtomikTypography

class TextViewAtom(
    override val textColor: AtomikColor,
    override val typography: AtomikTypography,
    override val fontFamily: AtomikFontFamily?,
) : Atom(), TextAtom {
    override val type: AtomType
        get() = AtomType.TEXT
}
