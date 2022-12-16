package me.kevinschildhorn.atomik.atoms

import me.kevinschildhorn.atomik.atoms.interfaces.AtomType
import me.kevinschildhorn.atomik.atoms.interfaces.ResourceAtom
import me.kevinschildhorn.atomik.color.SharedColor

class ImageAtom<T>(
    color: SharedColor,
    override val image: T,
) : Atom(), ResourceAtom<T> {
    override val type: AtomType = AtomType.IMAGE
}
