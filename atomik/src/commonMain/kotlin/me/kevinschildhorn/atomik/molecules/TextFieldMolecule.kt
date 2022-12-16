package me.kevinschildhorn.atomik.molecules

import me.kevinschildhorn.atomik.atoms.Atom
import me.kevinschildhorn.atomik.atoms.ImageAtom
import me.kevinschildhorn.atomik.atoms.TextFieldAtom
import me.kevinschildhorn.atomik.atoms.TextViewAtom

class TextFieldMolecule<T>(
    val errorAtom: TextViewAtom,
    val textEntryAtom: TextFieldAtom,
    val rightImageAtom: ImageAtom<T>,
) : BaseMolecule() {

    override val atoms: List<Atom>
        get() = listOf(errorAtom, textEntryAtom, rightImageAtom)
}
