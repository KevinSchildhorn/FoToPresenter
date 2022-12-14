package me.kevinschildhorn.common.design.atomicLib.atoms

import me.kevinschildhorn.common.design.atomicLib.atoms.interfaces.AtomType
import me.kevinschildhorn.common.design.atomicLib.atoms.interfaces.ResourceAtom
import me.kevinschildhorn.common.design.atomicLib.color.SharedColor

class ImageAtom<T>(
    color: SharedColor,
    override val image: T,
) : Atom(), ResourceAtom<T> {
    override val type: AtomType = AtomType.IMAGE
}
