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
        textColor = FotoColors.surfaceText,
        typography = FotoTypography.body,
        fontFamily = null,
    )

    val dialogButton: SimpleTextAtom = SimpleTextAtom(
        textColor = FotoColors.surfaceText,
        typography = FotoTypography.button,
        fontFamily = null,
    )

    val toastOverlay: SimpleTextAtom = SimpleTextAtom(
        textColor = FotoColors.primaryText,
        typography = FotoTypography.body,
        fontFamily = null,
    )
}