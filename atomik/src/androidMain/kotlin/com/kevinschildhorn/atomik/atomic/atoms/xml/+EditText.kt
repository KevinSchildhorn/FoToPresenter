package com.kevinschildhorn.atomik.atomic.atoms.xml

import android.widget.EditText
import com.kevinschildhorn.atomik.atomic.molecules.TextFieldMolecule

public fun EditText.applyMolecule(molecule: TextFieldMolecule) {
    setTextColor(molecule.textAtom.textColor.viewColor)
    //setBackgroundColor(molecule.backgroundColorAtom.color.viewColor) TODO Add later
    molecule.hintTextAtom?.let {
        setHintTextColor(it.textColor.viewColor)
    }

}