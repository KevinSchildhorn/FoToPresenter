package me.kevinschildhorn.atomik.atoms

import me.kevinschildhorn.atomik.atoms.interfaces.AtomType
import me.kevinschildhorn.atomik.atoms.interfaces.ColorAtom
import me.kevinschildhorn.atomik.atoms.interfaces.EnablableAtom
import me.kevinschildhorn.atomik.atoms.interfaces.TextAtom
import me.kevinschildhorn.atomik.color.SharedColor
import me.kevinschildhorn.atomik.color.SharedEnabledColor
import me.kevinschildhorn.atomik.font.SharedTypography

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
