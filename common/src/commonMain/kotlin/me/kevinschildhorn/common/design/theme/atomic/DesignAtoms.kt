package me.kevinschildhorn.common.design.theme.atomic

import me.kevinschildhorn.common.design.atomicLib.atoms.TextButtonAtom
import me.kevinschildhorn.common.design.atomicLib.atoms.TextFieldAtom
import me.kevinschildhorn.common.design.atomicLib.atoms.TextViewAtom
import me.kevinschildhorn.common.design.theme.Design

object DesignAtoms {
    object Buttons {
        val primaryButtonAtom = TextButtonAtom(
            textColor = Design.color().primaryText,
            color = Design.color().primary,
            disabledColor = Design.color().primary,
            font = Design.Typography.h2
        )
    }

    object TextView {
        val errorViewAtom = TextViewAtom(
            textColor = Design.color().errorText,
            textSize = 12.0,
            font = Design.Typography.h2
        )

        val title = TextViewAtom(
            textColor = Design.color().errorText,
            textSize = 12.0,
            font = Design.Typography.h2
        )
    }

    object TextField {
        val defaultTextFieldAtom = TextFieldAtom(
            borderColor = Design.color().primary,
            textColor = Design.color().primaryText,
            textSize = 16.0,
            font = Design.Typography.h2
        )
    }
}
