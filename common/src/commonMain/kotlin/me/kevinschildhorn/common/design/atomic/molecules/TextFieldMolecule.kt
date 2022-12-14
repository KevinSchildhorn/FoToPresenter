package me.kevinschildhorn.common.design.atomic.molecules

import me.kevinschildhorn.common.design.atomic.atoms.Atom
import me.kevinschildhorn.common.design.atomic.atoms.ImageAtom
import me.kevinschildhorn.common.design.atomic.atoms.TextFieldAtom
import me.kevinschildhorn.common.design.atomic.atoms.TextViewAtom

class TextFieldMolecule<T>(
    val errorAtom: TextViewAtom,
    val textEntryAtom: TextFieldAtom,
    val rightImageAtom: ImageAtom<T>,
) : BaseMolecule() {

    override val atoms: List<Atom>
        get() = listOf(errorAtom, textEntryAtom, rightImageAtom)
}
