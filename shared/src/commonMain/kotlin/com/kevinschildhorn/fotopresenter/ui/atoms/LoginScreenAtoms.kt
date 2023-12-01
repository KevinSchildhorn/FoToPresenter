package com.kevinschildhorn.fotopresenter.ui.atoms

import com.kevinschildhorn.atomik.atomic.atoms.interfaces.SimpleColorAtom
import com.kevinschildhorn.atomik.atomic.atoms.interfaces.SimpleTextAtom
import com.kevinschildhorn.atomik.atomic.molecules.TextFieldMolecule
import com.kevinschildhorn.atomik.color.base.AtomikColor

object LoginScreenAtoms {

    val textFieldMolecule = TextFieldMolecule(
        textAtom = textAtom(primaryText),
        backgroundColorAtom = SimpleColorAtom(surface),
        hintTextAtom = textAtom(surfaceText),
        errorTextAtom = textAtom(errorText),
        disabledColorAtom = SimpleColorAtom(surface),
    )

    private fun textAtom(color: AtomikColor) =
        SimpleTextAtom(
            textColor = primaryText,
            typography = subtitle,
            fontFamily = null
        )
}