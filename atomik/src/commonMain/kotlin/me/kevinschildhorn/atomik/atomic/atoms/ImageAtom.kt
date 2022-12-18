package me.kevinschildhorn.atomik.atomic.atoms

import me.kevinschildhorn.atomik.atomic.atoms.interfaces.AtomType
import me.kevinschildhorn.atomik.atomic.atoms.interfaces.ResourceAtom
import me.kevinschildhorn.atomik.color.Color

class ImageAtom<T>(
    color: Color,
    override val image: T,
) : Atom(), ResourceAtom<T> {
    override val type: AtomType = AtomType.IMAGE
}
