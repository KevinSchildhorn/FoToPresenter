package me.kevinschildhorn.common.atomic.atoms

import me.kevinschildhorn.common.atomic.atoms.interfaces.AtomType
import me.kevinschildhorn.common.atomic.atoms.interfaces.ResourceAtom
import me.kevinschildhorn.common.color.SharedColor

class ImageAtom<T>(
    color: SharedColor,
    override val image: T,
) : Atom(), ResourceAtom<T> {
    override val type: AtomType = AtomType.IMAGE
}