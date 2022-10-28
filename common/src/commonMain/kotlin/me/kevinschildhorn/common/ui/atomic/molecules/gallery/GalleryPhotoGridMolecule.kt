package me.kevinschildhorn.common.ui.atomic.molecules.gallery

import me.kevinschildhorn.common.ui.atomic.atoms.Atom
import me.kevinschildhorn.common.ui.atomic.atoms.ImageAtom
import me.kevinschildhorn.common.ui.atomic.molecules.Molecule

class GalleryPhotoGridMolecule(imageAtoms: List<ImageAtom>) : Molecule() {
    override val atoms: List<Atom> = imageAtoms
}