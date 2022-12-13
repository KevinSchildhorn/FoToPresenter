package me.kevinschildhorn.common.deprecated.atomic.atoms

import me.kevinschildhorn.common.deprecated.atomic.atoms.interfaces.AtomType
import me.kevinschildhorn.common.deprecated.atomic.atoms.interfaces.ResourceAtom
import me.kevinschildhorn.common.design.color.SharedColor

class ImageAtom<T>(
    color: SharedColor,
    override val image: T,
) : Atom(), ResourceAtom<T> {
    override val type: AtomType = AtomType.IMAGE
}
