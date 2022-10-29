package me.kevinschildhorn.common.ui.atomic.atoms

import me.kevinschildhorn.common.ui.atomic.designproperty.DesignProperty
import me.kevinschildhorn.common.ui.atomic.interfaces.TextAtomInterface
import me.kevinschildhorn.common.ui.style.color.SharedColor
import me.kevinschildhorn.common.ui.style.DesignTypography

class TextLabelAtom(
    override val design: List<DesignProperty> = emptyList(),
    override val typeStyle: DesignTypography,
) : Atom(type = AtomType.TEXT, design), TextAtomInterface