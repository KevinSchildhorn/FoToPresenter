package me.kevinschildhorn.common.ui.atomic.templates

import me.kevinschildhorn.common.ui.atomic.atoms.gallery.GalleryPhotoAtom
import me.kevinschildhorn.common.ui.atomic.organisms.Organism
import me.kevinschildhorn.common.ui.atomic.organisms.gallery.GalleryFolderOrganism

class GalleryGridTemplate(
    override val atoms: List<GalleryPhotoAtom> = listOf(GalleryPhotoAtom()),
    override val molecules: List<GalleryFolderOrganism> = listOf(GalleryFolderOrganism("", emptyList())),
    override val organism: List<Organism>
) : Template() {
    val defaultColumns: Int = 4
    val padding: Int = 5
}