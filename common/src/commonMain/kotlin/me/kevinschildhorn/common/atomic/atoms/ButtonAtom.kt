package me.kevinschildhorn.common.atomic.atoms

import me.kevinschildhorn.common.atomic.atoms.interfaces.AtomType
import me.kevinschildhorn.common.atomic.atoms.interfaces.EnablableAtom
import me.kevinschildhorn.common.color.SharedColor
import me.kevinschildhorn.common.color.SharedEnabledColor

class ButtonAtom(
    override val color: SharedColor,
    private val disabledColor: SharedColor,
    override val height: Int = 44
) : Atom(), EnablableAtom {
    override val type = AtomType.BUTTON

    override val enabledColor: SharedEnabledColor
        get() = SharedEnabledColor(color, disabledColor)
}