package me.kevinschildhorn.common.ui
import platform.CoreGraphics.CGFloat
import platform.UIKit.*

actual class SharedColor actual constructor(hex:Long) {

    private val color:UIColor

    init {
        val r = (hex and 0xFF000000 shr 24).toDouble()
        val g = (hex and 0xFF0000 shr 16).toDouble()
        val b = (hex and 0xFF00 shr 8).toDouble()
        val a = (hex and 0xFF).toDouble()
        this.color = UIColor(r, g, b, a)
    }
}