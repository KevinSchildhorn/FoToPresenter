package me.kevinschildhorn.common.atomic.atoms

import me.kevinschildhorn.common.atomic.atoms.interfaces.AtomType
import me.kevinschildhorn.common.color.SharedColor

class TextAtom(
    override val color: SharedColor,
) : Atom() {
    override val type = AtomType.TEXT
    override val height: Int? = null
}