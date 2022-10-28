package me.kevinschildhorn.common.ui.atomic.templates

import me.kevinschildhorn.common.ui.atomic.atoms.Atom
import me.kevinschildhorn.common.ui.atomic.molecules.Molecule
import me.kevinschildhorn.common.ui.atomic.organisms.Organism

abstract class Template {
    abstract val atoms: List<Atom>
    abstract val molecules: List<Molecule>
    abstract val organism: List<Organism>
}