package com.kevinschildhorn.fotopresenter.ui.screens.login

import com.kevinschildhorn.atomik.atomic.atoms.TextViewAtom
import com.kevinschildhorn.atomik.atomic.atoms.interfaces.SimpleColorAtom
import com.kevinschildhorn.atomik.atomic.atoms.interfaces.SimpleTextAtom
import com.kevinschildhorn.atomik.atomic.molecules.OutlinedTextFieldMolecule
import com.kevinschildhorn.atomik.atomic.molecules.TextButtonMolecule
import com.kevinschildhorn.atomik.color.base.AtomikColor
import com.kevinschildhorn.atomik.typography.base.AtomikTypography
import com.kevinschildhorn.fotopresenter.ui.atoms.FotoColors
import com.kevinschildhorn.fotopresenter.ui.atoms.FotoTypography
import com.kevinschildhorn.fotopresenter.ui.atoms.Padding

object LoginScreenAtoms {
    fun title(
        textColor: AtomikColor = FotoColors.backgroundText,
        typography: AtomikTypography = FotoTypography.h1,
    ) = SimpleTextAtom(
        textColor = textColor,
        typography = typography,
        fontFamily = null,
    )

    val errorView =
        TextViewAtom(
            textColor = FotoColors.errorText,
            typography = FotoTypography.body,
            backgroundColor = FotoColors.error,
            paddingHorizontal = Padding.MEDIUM.rawValue,
            paddingVertical = Padding.MEDIUM.rawValue,
        )

    val primaryButton =
        TextButtonMolecule(
            color = FotoColors.primary,
            disabledColor = FotoColors.disabled,
            radius = 15,
            textAtom =
                SimpleTextAtom(
                    textColor = FotoColors.primaryText,
                    typography = FotoTypography.button,
                    fontFamily = null,
                ),
        )
    val textFieldMolecule =
        OutlinedTextFieldMolecule(
            textAtom = textAtom(FotoColors.primaryText),
            backgroundColorAtom = SimpleColorAtom(FotoColors.surface),
            hintTextAtom = textAtom(FotoColors.surfaceText),
            errorTextAtom = textAtom(FotoColors.errorText),
            disabledColorAtom = SimpleColorAtom(FotoColors.surface),
            focusedBorderColor = FotoColors.backgroundText,
            radius = 5,
        )

    private fun textAtom(color: AtomikColor) =
        SimpleTextAtom(
            textColor = color,
            typography = FotoTypography.subtitle,
            fontFamily = null,
        )
}
