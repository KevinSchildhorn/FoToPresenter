package me.kevinschildhorn.common.ui.atomic.molecules

import me.kevinschildhorn.common.ui.atomic.atoms.Atom
import me.kevinschildhorn.common.ui.atomic.atoms.AtomType
import me.kevinschildhorn.common.ui.atomic.designproperty.DesignProperty

abstract class Molecule {
    abstract val type: AtomType
    abstract val atoms: List<Atom>
    abstract val design: List<DesignProperty>
}