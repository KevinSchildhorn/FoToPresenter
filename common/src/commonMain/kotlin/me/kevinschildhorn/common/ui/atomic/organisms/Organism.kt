package me.kevinschildhorn.common.ui.atomic.organisms

import me.kevinschildhorn.common.ui.atomic.atoms.Atom
import me.kevinschildhorn.common.ui.atomic.molecules.Molecule

abstract class Organism {
    abstract val atoms: List<Atom>
    abstract val molecules: List<Molecule>
}