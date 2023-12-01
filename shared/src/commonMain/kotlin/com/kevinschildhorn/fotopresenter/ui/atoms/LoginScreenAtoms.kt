package com.kevinschildhorn.fotopresenter.ui.atoms

import com.kevinschildhorn.atomik.atomic.atoms.interfaces.SimpleColorAtom
import com.kevinschildhorn.atomik.atomic.atoms.interfaces.SimpleTextAtom
import com.kevinschildhorn.atomik.atomic.molecules.OutlinedTextFieldMolecule
import com.kevinschildhorn.atomik.color.base.AtomikColor

object LoginScreenAtoms {
    val textFieldMolecule = OutlinedTextFieldMolecule(
        textAtom = textAtom(primaryText),
        backgroundColorAtom = SimpleColorAtom(surface),
        hintTextAtom = textAtom(surfaceText),
        errorTextAtom = textAtom(errorText),
        disabledColorAtom = SimpleColorAtom(surface),
        focusedBorderColor = backgroundText,
        radius = 5,
    )

    private fun textAtom(color: AtomikColor) =
        SimpleTextAtom(
            textColor = color,
            typography = subtitle,
            fontFamily = null
        )
}