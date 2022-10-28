package me.kevinschildhorn.common.ui.atomic.atoms

abstract class FigmaAtom : Atom() {
    open val width:Int? = null
    open val height:Int? = null
    open val constrainProportions: Boolean = false
    open val cornerRadius: Int = 0
    open val marginX: Int = 0
    open val marginY: Int = marginX
}