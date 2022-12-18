package me.kevinschildhorn.atomik.atomic.atoms

import me.kevinschildhorn.atomik.atomic.atoms.interfaces.AtomType
import me.kevinschildhorn.atomik.atomic.atoms.interfaces.ColorAtom
import me.kevinschildhorn.atomik.atomic.atoms.interfaces.EnablableAtom
import me.kevinschildhorn.atomik.atomic.atoms.interfaces.TextAtom
import me.kevinschildhorn.atomik.color.Color
import me.kevinschildhorn.atomik.color.SharedEnabledColor
import me.kevinschildhorn.atomik.typography.base.AtomikTypography

class TextButtonAtom(
    override val textColor: Color,
    override val font: AtomikTypography,
    override val color: Color,
    private val disabledColor: Color,
) : Atom(), EnablableAtom, TextAtom, ColorAtom {

    override val type: AtomType = AtomType.BUTTON
    override val enabledColor: SharedEnabledColor
        get() = SharedEnabledColor(color, disabledColor)
}
