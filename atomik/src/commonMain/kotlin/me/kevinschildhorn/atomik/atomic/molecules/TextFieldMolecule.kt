package me.kevinschildhorn.atomik.atomic.molecules

import me.kevinschildhorn.atomik.atomic.atoms.Atom
import me.kevinschildhorn.atomik.atomic.atoms.ImageAtom
import me.kevinschildhorn.atomik.atomic.atoms.TextFieldAtom
import me.kevinschildhorn.atomik.atomic.atoms.TextViewAtom

class TextFieldMolecule<T>(
    val errorAtom: TextViewAtom,
    val textEntryAtom: TextFieldAtom,
    val rightImageAtom: ImageAtom<T>,
) : BaseMolecule() {

    override val atoms: List<Atom>
        get() = listOf(errorAtom, textEntryAtom, rightImageAtom)
}
