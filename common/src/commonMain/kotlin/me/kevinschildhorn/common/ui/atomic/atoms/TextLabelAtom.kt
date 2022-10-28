package me.kevinschildhorn.common.ui.atomic.atoms

import me.kevinschildhorn.common.ui.atomic.interfaces.TextAtomInterface
import me.kevinschildhorn.common.ui.style.color.SharedColor
import me.kevinschildhorn.common.ui.style.DesignTypography

class TextLabelAtom(
    val text:String,
    override val typeStyle: DesignTypography,
    val textColor: SharedColor,
) : Atom(), TextAtomInterface