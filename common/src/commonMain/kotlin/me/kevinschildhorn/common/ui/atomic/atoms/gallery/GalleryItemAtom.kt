package me.kevinschildhorn.common.ui.atomic.atoms.gallery

import me.kevinschildhorn.common.ui.atomic.atoms.Atom
import me.kevinschildhorn.common.ui.atomic.atoms.FigmaAtom

open class GalleryItemAtom : FigmaAtom() {
    override val height: Int? = 1
    override val width: Int? = 1
    override val constrainProportions: Boolean = true
    override val cornerRadius: Int = 25
    val marginAll: Int = 5
}