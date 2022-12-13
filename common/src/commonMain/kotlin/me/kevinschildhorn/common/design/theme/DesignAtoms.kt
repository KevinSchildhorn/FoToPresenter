package me.kevinschildhorn.common.design.theme

import me.kevinschildhorn.common.deprecated.atomic.atoms.TextButtonAtom
import me.kevinschildhorn.common.deprecated.atomic.atoms.TextFieldAtom
import me.kevinschildhorn.common.deprecated.atomic.atoms.TextViewAtom
import me.kevinschildhorn.common.design.font.SharedFont
import me.kevinschildhorn.common.design.font.Weight

object DesignAtoms {
    object Buttons {
        val primaryButtonAtom = TextButtonAtom(
            textColor = DesignColors.textColor,
            color = DesignColors.primary,
            disabledColor = DesignColors.secondary,
            font = SharedFont("Helvetica", Weight.NORMAL)
        )
    }

    object TextView {
        val errorViewAtom = TextViewAtom(
            textColor = DesignColors.error,
            textSize = 12.0,
            font = SharedFont("Helvetica", Weight.NORMAL)
        )
    }

    object TextField {
        val defaultTextFieldAtom = TextFieldAtom(
            borderColor = DesignColors.primary,
            textColor = DesignColors.textColor,
            textSize = 16.0,
            font = SharedFont("Helvetica", Weight.NORMAL)
        )
    }
}
