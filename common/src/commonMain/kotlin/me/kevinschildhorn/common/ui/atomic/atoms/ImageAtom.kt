package me.kevinschildhorn.common.ui.atomic.atoms

import me.kevinschildhorn.common.ui.atomic.ImageResource
import me.kevinschildhorn.common.ui.atomic.designproperty.DesignProperty
import me.kevinschildhorn.common.ui.atomic.interfaces.ImageAtomInterface
import me.kevinschildhorn.common.ui.style.image.CropStyle

class ImageAtom(
    val image: ImageResource,
    override val imageName: String?,
    override val cropStyle: CropStyle,
    override val design: List<DesignProperty> = emptyList(),
) : Atom(type = AtomType.GROUP, design), ImageAtomInterface