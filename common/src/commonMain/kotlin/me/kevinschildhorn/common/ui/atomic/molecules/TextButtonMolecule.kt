package me.kevinschildhorn.common.ui.atomic.molecules

import me.kevinschildhorn.common.ui.atomic.atoms.Atom
import me.kevinschildhorn.common.ui.atomic.atoms.AtomType
import me.kevinschildhorn.common.ui.atomic.atoms.TextLabelAtom
import me.kevinschildhorn.common.ui.atomic.designproperty.*
import me.kevinschildhorn.common.ui.style.DesignTypography
import me.kevinschildhorn.common.ui.style.color.SharedColorStyleGuide

class TextButtonMolecule(
    private val colorGuide: SharedColorStyleGuide,
    override val type:AtomType = AtomType.BUTTON,
) : Molecule() {
    override val atoms: List<Atom>
        get() = listOf(
            TextLabelAtom(
                typeStyle = DesignTypography.BUTTON,
                design = listOf(
                    MarginDesignProperty(margin = 11)
                )
            ),
            Atom(
                type = AtomType.GROUP,
                design = listOf(
                    ColorDesignProperty(colorGuide.primary),
                    SizeDesignProperty()
                )
            ),
        )

    override val design: List<DesignProperty>
        get() = listOf(
            CornerDesignProperty(15),
            IntrinsicSizeDesignProperty()
        )
}