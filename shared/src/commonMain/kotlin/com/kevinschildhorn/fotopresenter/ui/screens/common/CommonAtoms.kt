package com.kevinschildhorn.fotopresenter.ui.screens.common

import com.kevinschildhorn.atomik.atomic.atoms.interfaces.SimpleTextAtom
import com.kevinschildhorn.fotopresenter.ui.atoms.FotoColors
import com.kevinschildhorn.fotopresenter.ui.atoms.FotoTypography

object CommonAtoms {

    val dialogTitle: SimpleTextAtom = SimpleTextAtom(
        textColor = FotoColors.secondaryText,
        typography = FotoTypography.h4,
        fontFamily = null,
    )

    val dialogMessage: SimpleTextAtom = SimpleTextAtom(
        textColor = FotoColors.secondaryText,
        typography = FotoTypography.body,
        fontFamily = null,
    )
}