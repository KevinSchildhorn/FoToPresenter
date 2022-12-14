package me.kevinschildhorn.common.design.theme.atomic

import me.kevinschildhorn.common.design.atomicLib.atoms.ImageAtom
import me.kevinschildhorn.common.design.atomicLib.molecules.TextFieldMolecule
import me.kevinschildhorn.common.design.theme.Design

object DesignMolecules {
    object TextField {
        val defaultTextField = TextFieldMolecule(
            errorAtom = DesignAtoms.TextView.errorViewAtom,
            textEntryAtom = DesignAtoms.TextField.defaultTextFieldAtom,
            rightImageAtom = ImageAtom(Design.color().primaryText, "")
        )
    }
}
