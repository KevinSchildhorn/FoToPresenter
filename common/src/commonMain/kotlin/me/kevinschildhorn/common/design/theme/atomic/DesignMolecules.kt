package me.kevinschildhorn.common.design.theme.atomic

import me.kevinschildhorn.common.design.atomicLib.atoms.ImageAtom
import me.kevinschildhorn.common.design.atomicLib.molecules.TextFieldMolecule
import me.kevinschildhorn.common.design.theme.UIDesign

object DesignMolecules {
    object TextField {
        val defaultTextField = TextFieldMolecule(
            errorAtom = DesignAtoms.TextView.errorViewAtom,
            textEntryAtom = DesignAtoms.TextField.defaultTextFieldAtom,
            rightImageAtom = ImageAtom(UIDesign.color().primaryText, "")
        )
    }
}
