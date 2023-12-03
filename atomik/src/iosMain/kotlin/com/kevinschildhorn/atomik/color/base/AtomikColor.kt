package com.kevinschildhorn.atomik.color.base

import platform.UIKit.UIColor

@Suppress("SpellCheckingInspection")
/*
 * iOS implementation of [AtomikColor]
 *
 * @property uiColor the AtomikColor as an iOS UIColor [platform.UIKit.UIColor]
 * @property hexString The hex value of the color (#RRGGBBAA)
 * @property r The red Value (0-255)
 * @property g The red Value (0-255)
 * @property b The red Value (0-255)
 * @property a The alpha Value (0-1) (Defaults to 1)
 */
public actual class AtomikColor {
    public actual val hexString: String
    public actual val r: Int
    public actual val g: Int
    public actual val b: Int
    public actual val a: Float

    public val uiColor: UIColor
        get() = UIColor(
            red = r.toDouble() / 255,
            green = g.toDouble() / 255,
            blue = b.toDouble() / 255,
            alpha = a.toDouble()
        )

    public actual constructor(hex: Long) {
        val data = AtomikColorData(hex)
        this.hexString = data.hexString
        this.r = data.r
        this.g = data.g
        this.b = data.b
        this.a = data.a
    }

    public actual constructor(r: Int, g: Int, b: Int, a: Float?) {
        val data = AtomikColorData(r, g, b, a)
        this.hexString = data.hexString
        this.r = data.r
        this.g = data.g
        this.b = data.b
        this.a = data.a
    }
}
