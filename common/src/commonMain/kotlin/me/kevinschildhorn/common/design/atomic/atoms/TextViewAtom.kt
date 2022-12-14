package me.kevinschildhorn.common.design.atomic.atoms

import me.kevinschildhorn.common.design.atomic.atoms.interfaces.AtomType
import me.kevinschildhorn.common.design.atomic.atoms.interfaces.TextAtom
import me.kevinschildhorn.common.design.color.SharedColor
import me.kevinschildhorn.common.design.font.SharedFont

class TextViewAtom(
    override val textColor: SharedColor,
    override val textSize: Double,
    override val font: SharedFont,
) : Atom(), TextAtom {
    override val type: AtomType
        get() = AtomType.TEXT
}
