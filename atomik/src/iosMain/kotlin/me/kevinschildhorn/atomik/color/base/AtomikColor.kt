package me.kevinschildhorn.atomik.color.base

import platform.UIKit.UIColor

actual class AtomikColor actual constructor(hex: Long) {

    val platformColor: UIColor

    init {
        val a = (hex and 0xFF000000 shr 24).toDouble() / 255
        val r = (hex and 0xFF0000 shr 16).toDouble() / 255
        val g = (hex and 0xFF00 shr 8).toDouble() / 255
        val b = (hex and 0xFF).toDouble() / 255
        this.platformColor = UIColor(red = r, green = g, blue = b, alpha = a)
    }
}
