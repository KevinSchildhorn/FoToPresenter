package com.kevinschildhorn.atomik.atomic.atoms

import com.kevinschildhorn.atomik.atomic.atoms.interfaces.AtomType
import com.kevinschildhorn.atomik.atomic.atoms.interfaces.ResourceAtom
import com.kevinschildhorn.atomik.color.base.AtomikColor


public class ImageAtom<T>(
    public val color: AtomikColor,
    override val image: T,
) : Atom(), ResourceAtom<T> {
    override val type: AtomType = AtomType.IMAGE
}
