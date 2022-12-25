package me.kevinschildhorn.atomik.atomic.atoms

import me.kevinschildhorn.atomik.DesignSystem
import me.kevinschildhorn.atomik.atomic.atoms.interfaces.AtomType
import me.kevinschildhorn.atomik.atomic.atoms.interfaces.ColorAtom
import me.kevinschildhorn.atomik.atomic.atoms.interfaces.EnablableAtom
import me.kevinschildhorn.atomik.atomic.atoms.interfaces.TextAtom
import me.kevinschildhorn.atomik.color.base.AtomikColor
import me.kevinschildhorn.atomik.color.base.AtomikEnabledColor
import me.kevinschildhorn.atomik.typography.base.AtomikFontFamily
import me.kevinschildhorn.atomik.typography.base.AtomikTypography

class TextButtonAtom(
    override val textColor: AtomikColor,
    override val typography: AtomikTypography,
    override val color: AtomikColor,
    override val fontFamily: AtomikFontFamily?,
    private val disabledColor: AtomikColor,
) : Atom(), EnablableAtom, TextAtom, ColorAtom {

    override val type: AtomType = AtomType.BUTTON
    override val enabledColor: AtomikEnabledColor
        get() = AtomikEnabledColor(color, disabledColor)
}
