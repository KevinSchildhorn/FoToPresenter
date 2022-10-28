package me.kevinschildhorn.common.ui.atomic.organisms.gallery

import me.kevinschildhorn.common.ui.atomic.atoms.Atom
import me.kevinschildhorn.common.ui.atomic.atoms.ImageAtom
import me.kevinschildhorn.common.ui.atomic.atoms.TextLabelAtom
import me.kevinschildhorn.common.ui.atomic.molecules.Molecule
import me.kevinschildhorn.common.ui.atomic.organisms.Organism
import me.kevinschildhorn.common.ui.style.DesignColors
import me.kevinschildhorn.common.ui.style.DesignTypography

class GalleryFolderOrganism(val text: String, imageAtoms: List<ImageAtom>) : Organism() {
    private val labelAtom = TextLabelAtom(text, DesignTypography.BUTTON, DesignColors.light.surface)
    override val atoms: List<Atom> = imageAtoms + listOf(labelAtom)
}