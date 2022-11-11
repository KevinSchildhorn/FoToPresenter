package me.kevinschildhorn.common.atomic.molecules

import me.kevinschildhorn.common.atomic.atoms.Atom
import me.kevinschildhorn.common.atomic.atoms.ImageAtom
import me.kevinschildhorn.common.atomic.atoms.TextFieldAtom
import me.kevinschildhorn.common.atomic.atoms.TextViewAtom

class TextFieldMolecule<T>(
    val errorAtom: TextViewAtom,
    val textEntryAtom: TextFieldAtom,
    val rightImageAtom: ImageAtom<T>,
) : BaseMolecule() {

    override val atoms: List<Atom>
        get() = listOf(errorAtom, textEntryAtom, rightImageAtom)
}
