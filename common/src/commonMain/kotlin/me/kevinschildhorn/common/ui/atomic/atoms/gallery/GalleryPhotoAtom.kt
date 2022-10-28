package me.kevinschildhorn.common.ui.atomic.atoms.gallery

import me.kevinschildhorn.common.ui.atomic.interfaces.ImageAtomInterface
import me.kevinschildhorn.common.ui.style.image.CropStyle

class GalleryPhotoAtom(
    override val cropStyle: CropStyle = CropStyle.FILL,
    override val imageName: String? = null
) : GalleryItemAtom(), ImageAtomInterface