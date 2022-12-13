package me.kevinschildhorn.common.deprecated.atomic.molecules

import me.kevinschildhorn.common.deprecated.atomic.atoms.Atom
import me.kevinschildhorn.common.deprecated.atomic.atoms.ImageAtom
import me.kevinschildhorn.common.deprecated.atomic.atoms.TextFieldAtom
import me.kevinschildhorn.common.deprecated.atomic.atoms.TextViewAtom

class TextFieldMolecule<T>(
    val errorAtom: TextViewAtom,
    val textEntryAtom: TextFieldAtom,
    val rightImageAtom: ImageAtom<T>,
) : BaseMolecule() {

    override val atoms: List<Atom>
        get() = listOf(errorAtom, textEntryAtom, rightImageAtom)
}
