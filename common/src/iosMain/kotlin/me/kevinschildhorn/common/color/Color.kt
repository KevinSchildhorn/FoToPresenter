package me.kevinschildhorn.common.color
import platform.UIKit.*

actual class SharedColor actual constructor(hex:Long) {

    val iOSColor:UIColor

    init {
        val r = (hex and 0xFF000000 shr 24).toDouble()
        val g = (hex and 0xFF0000 shr 16).toDouble()
        val b = (hex and 0xFF00 shr 8).toDouble()
        val a = (hex and 0xFF).toDouble()
        this.iOSColor = UIColor(red = r,green = g,blue = b,alpha = a)
    }
}