package me.kevinschildhorn.common.design.theme.atomic

import me.kevinschildhorn.atomik.atoms.ImageAtom
import me.kevinschildhorn.atomik.molecules.TextFieldMolecule
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
