package me.kevinschildhorn.common.design.theme

import me.kevinschildhorn.common.deprecated.atomic.atoms.ImageAtom
import me.kevinschildhorn.common.deprecated.atomic.molecules.TextFieldMolecule

object DesignMolecules {
    object TextField {
        val defaultTextField = TextFieldMolecule(
            errorAtom = DesignAtoms.TextView.errorViewAtom,
            textEntryAtom = DesignAtoms.TextField.defaultTextFieldAtom,
            rightImageAtom = ImageAtom(DesignColors.primary, "")
        )
    }
}
