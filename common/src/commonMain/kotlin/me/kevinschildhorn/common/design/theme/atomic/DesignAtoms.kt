package me.kevinschildhorn.common.design.theme.atomic

import me.kevinschildhorn.common.design.atomicLib.atoms.TextButtonAtom
import me.kevinschildhorn.common.design.atomicLib.atoms.TextFieldAtom
import me.kevinschildhorn.common.design.atomicLib.atoms.TextViewAtom
import me.kevinschildhorn.common.design.theme.UIDesign

object DesignAtoms {
    object Buttons {
        val primaryButtonAtom = TextButtonAtom(
            textColor = UIDesign.color().primaryText,
            color = UIDesign.color().primary,
            disabledColor = UIDesign.color().primary,
            font = UIDesign.Typography.h2
        )
    }

    object TextView {
        val errorViewAtom = TextViewAtom(
            textColor = UIDesign.color().errorText,
            textSize = 12.0,
            font = UIDesign.Typography.h2
        )

        val title = TextViewAtom(
            textColor = UIDesign.color().errorText,
            textSize = 12.0,
            font = UIDesign.Typography.h2
        )
    }

    object TextField {
        val defaultTextFieldAtom = TextFieldAtom(
            borderColor = UIDesign.color().primary,
            textColor = UIDesign.color().primaryText,
            textSize = 16.0,
            font = UIDesign.Typography.h2
        )
    }
}
