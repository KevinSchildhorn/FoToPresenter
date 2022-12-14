package me.kevinschildhorn.common.design.atomic.atoms

import me.kevinschildhorn.common.design.atomic.atoms.interfaces.AtomType
import me.kevinschildhorn.common.design.atomic.atoms.interfaces.ColorAtom
import me.kevinschildhorn.common.design.atomic.atoms.interfaces.EnablableAtom
import me.kevinschildhorn.common.design.atomic.atoms.interfaces.TextAtom
import me.kevinschildhorn.common.design.color.SharedColor
import me.kevinschildhorn.common.design.color.SharedEnabledColor
import me.kevinschildhorn.common.design.font.SharedFont

class TextButtonAtom(
    override val textColor: SharedColor,
    override val font: SharedFont,
    override val color: SharedColor,
    private val disabledColor: SharedColor,
) : Atom(), EnablableAtom, TextAtom, ColorAtom {

    override val type: AtomType = AtomType.BUTTON
    override val enabledColor: SharedEnabledColor
        get() = SharedEnabledColor(color, disabledColor)
    override val textSize: Double = 44.0
}
