package me.kevinschildhorn.common.ui.atomic.molecules

import me.kevinschildhorn.common.ui.atomic.atoms.Atom

abstract class Molecule {
    abstract val atoms: List<Atom>
}