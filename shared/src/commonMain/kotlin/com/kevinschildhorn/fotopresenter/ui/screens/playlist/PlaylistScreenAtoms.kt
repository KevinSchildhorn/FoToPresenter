package com.kevinschildhorn.fotopresenter.ui.screens.playlist

import com.kevinschildhorn.atomik.atomic.atoms.interfaces.SimpleTextAtom
import com.kevinschildhorn.fotopresenter.ui.atoms.FotoColors
import com.kevinschildhorn.fotopresenter.ui.atoms.FotoTypography

object PlaylistScreenAtoms {

    val title:SimpleTextAtom = SimpleTextAtom(
        textColor = FotoColors.secondaryText,
        typography = FotoTypography.h4,
        fontFamily = null,
    )

    val rowTitle:SimpleTextAtom = SimpleTextAtom(
        textColor = FotoColors.secondaryText,
        typography = FotoTypography.button,
        fontFamily = null,
    )
}