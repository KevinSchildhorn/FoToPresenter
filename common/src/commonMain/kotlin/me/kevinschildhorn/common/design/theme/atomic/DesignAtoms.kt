package me.kevinschildhorn.common.design.theme.atomic

import me.kevinschildhorn.atomik.atoms.TextButtonAtom
import me.kevinschildhorn.atomik.atoms.TextFieldAtom
import me.kevinschildhorn.atomik.atoms.TextViewAtom
import me.kevinschildhorn.common.design.theme.UIDesign

object DesignAtoms {
    object Buttons {
        val primaryButtonAtom = TextButtonAtom(
            textColor = UIDesign.color().primaryText,
            color = UIDesign.color().primary,
            disabledColor = UIDesign.color().primary,
            font = UIDesign.typography.h2
        )
    }

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
    }
}
