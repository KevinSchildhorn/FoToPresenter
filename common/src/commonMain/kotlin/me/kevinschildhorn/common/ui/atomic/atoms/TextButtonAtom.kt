package me.kevinschildhorn.common.ui.atomic.atoms

import me.kevinschildhorn.common.ui.atomic.interfaces.TextAtomInterface
import me.kevinschildhorn.common.ui.style.DesignTypography

abstract class TextButtonAtom(
    val text:String = "",
    val onClick:() -> Unit,
) : FigmaAtom(), TextAtomInterface {
    abstract override val height:Int?
    abstract override val width: Int?
    abstract override val constrainProportions: Boolean
    abstract override val typeStyle: DesignTypography
}