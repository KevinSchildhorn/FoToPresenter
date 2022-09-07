package me.kevinschildhorn.common.atomic.atoms

import me.kevinschildhorn.common.atomic.atoms.interfaces.AtomType
import me.kevinschildhorn.common.ui.SharedColor

abstract class Atom {
    abstract val type: AtomType
    abstract val color:SharedColor
    abstract val height:Int?
}