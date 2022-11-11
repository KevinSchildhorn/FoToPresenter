package me.kevinschildhorn.common.theme

import me.kevinschildhorn.common.atomic.atoms.ImageAtom
import me.kevinschildhorn.common.atomic.molecules.TextFieldMolecule

object DesignMolecules {
    object TextField {
        val defaultTextField = TextFieldMolecule(
            errorAtom = DesignAtoms.TextView.errorViewAtom,
            textEntryAtom = DesignAtoms.TextField.defaultTextFieldAtom,
            rightImageAtom = ImageAtom(DesignColors.primary, "")
        )
    }
}
