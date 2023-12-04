package com.kevinschildhorn.atomik.color.base

/**
 * A common implementation of a Color
 *
 * This contains platform specific implementations in the actual versions. Can be created from
 * either a hex Long or an RGBA value
 *
 * @property hexString The hex value of the color (#RRGGBBAA)
 * @property r The red Value (0-255)
 * @property g The red Value (0-255)
 * @property b The red Value (0-255)
 * @property a The alpha Value (0-1) (Defaults to 1)
 */
@Suppress("SpellCheckingInspection")
public actual class AtomikColor {
    public actual val hexString: String
    public actual val r: Int
    public actual val g: Int
    public actual val b: Int
    public actual val a: Float

    /**
     * Constructor with hex variable
     *
     * @constructor Creates a color based on the hex value(i.e. 0xFFFFFF)
     * @param hex - The Hex Value of the color (#RRGGBBAA)
     */
    public actual constructor(hex: Long) {
        val data = AtomikColorData(hex)
        this.hexString = data.hexString
        this.r = data.r
        this.g = data.g
        this.b = data.b
        this.a = data.a
    }

    /**
     * Constructor with r, g, b, a
     *
     * @constructor Creates a color based on the rgb values. Alpha defaults to 1.
     *  @param r - Red value (0-255)
     *  @param g - Green Value (0-255)
     *  @param b - Blue Value (0-255)
     *  @param a - Alpha value (0-1) (defaults to 1)
     */
    public actual constructor(r: Int, g: Int, b: Int, a: Float?) {
        val data = AtomikColorData(r, g, b, a)
        this.hexString = data.hexString
        this.r = data.r
        this.g = data.g
        this.b = data.b
        this.a = data.a
    }
}
