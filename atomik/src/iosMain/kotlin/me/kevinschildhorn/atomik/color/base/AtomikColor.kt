package me.kevinschildhorn.atomik.color.base

import platform.UIKit.UIColor

actual class AtomikColor actual constructor(hex: Long) {

    val platformColor: UIColor

    init {
        val a = (hex and 0xFF000000 shr 24).toDouble()
        val r = (hex and 0xFF0000 shr 16).toDouble()
        val g = (hex and 0xFF00 shr 8).toDouble()
        val b = (hex and 0xFF).toDouble()
        this.iOSColor = UIColor(red = r, green = g, blue = b, alpha = a)
    }
}
