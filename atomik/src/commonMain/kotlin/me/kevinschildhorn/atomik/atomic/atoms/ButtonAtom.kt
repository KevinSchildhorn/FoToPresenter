package me.kevinschildhorn.atomik.atomic.atoms

import me.kevinschildhorn.atomik.atomic.atoms.interfaces.AtomType
import me.kevinschildhorn.atomik.atomic.atoms.interfaces.ColorAtom
import me.kevinschildhorn.atomik.atomic.atoms.interfaces.EnablableAtom
import me.kevinschildhorn.atomik.atomic.atoms.interfaces.FixedSizeAtom
import me.kevinschildhorn.atomik.atomic.atoms.interfaces.RoundedAtom
import me.kevinschildhorn.atomik.color.base.AtomikColor
import me.kevinschildhorn.atomik.color.base.AtomikEnabledColor

class ButtonAtom(
    override val enabledColor: AtomikEnabledColor,
    override val radius: Int,
    override val height: Int?,
) : Atom(), EnablableAtom, RoundedAtom, FixedSizeAtom {
    override val type: AtomType = AtomType.BUTTON

    override val width: Int? = null
}