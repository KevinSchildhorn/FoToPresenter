package me.kevinschildhorn.common.design.theme.atomic

import me.kevinschildhorn.atomik.atomic.atoms.TextButtonAtom
import me.kevinschildhorn.atomik.atomic.molecules.TextButtonMolecule
import me.kevinschildhorn.atomik.color.disabledColor
import me.kevinschildhorn.atomik.typography.base.TypographyType
import me.kevinschildhorn.common.design.theme.designSystem

object DesignAtoms {
    /*
    object TextView {
        val labelAtom = TextViewAtom(
            textColor = designSystem.colorSet.backgroundText,
            typography = designSystem.typographySet.getTypography(TypographyType.H3),
        )
    }*/
    object Buttons {
        val primaryButtonMolecule = TextButtonMolecule(
            color = designSystem.colorSet.primary,
            disabledColor = disabledColor,
            radius = 15,
            height = 44,
            textColor = designSystem.colorSet.primaryText,
            typography = designSystem.typographySet.getTypography(TypographyType.Button),
            fontFamily = designSystem.fontFamily,
        )
    }
    /*

    object TextView {
        val errorViewAtom = TextViewAtom(
            textColor = UIDesign.color().errorText,
            font = UIDesign.typography.h2
        )

        val screenTitle = TextViewAtom(
            textColor = UIDesign.color().backgroundText,
            font = UIDesign.typography.h3
        )
    }

    object TextField {
        val defaultTextFieldAtom = TextFieldAtom(
            borderColor = UIDesign.color().primary,
            textColor = UIDesign.color().primaryText,
            font = UIDesign.typography.h2
        )
    }*/
}
