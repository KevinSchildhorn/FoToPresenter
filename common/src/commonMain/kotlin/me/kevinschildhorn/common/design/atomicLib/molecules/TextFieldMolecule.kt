package me.kevinschildhorn.common.design.atomicLib.molecules

import me.kevinschildhorn.common.design.atomicLib.atoms.Atom
import me.kevinschildhorn.common.design.atomicLib.atoms.ImageAtom
import me.kevinschildhorn.common.design.atomicLib.atoms.TextFieldAtom
import me.kevinschildhorn.common.design.atomicLib.atoms.TextViewAtom

class TextFieldMolecule<T>(
    val errorAtom: TextViewAtom,
    val textEntryAtom: TextFieldAtom,
    val rightImageAtom: ImageAtom<T>,
) : BaseMolecule() {

    override val atoms: List<Atom>
        get() = listOf(errorAtom, textEntryAtom, rightImageAtom)
}
