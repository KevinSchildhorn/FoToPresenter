package com.kevinschildhorn.atomik.color.base

import androidx.compose.ui.graphics.Color

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
public expect class AtomikColor {

    public val hexString: String
    public val r: Int
    public val g: Int
    public val b: Int
    public val a: Float

    /**
     * Constructor with hex variable
     *
     * @constructor Creates a color based on the hex value(i.e. 0xFFFFFF)
     * @param hex - The Hex Value of the color (#RRGGBBAA)
     */
    public constructor(hex: Long)

    /**
     * Constructor with r, g, b, a
     *
     * @constructor Creates a color based on the rgb values. Alpha defaults to 1.
     *  @param r - Red value (0-255)
     *  @param g - Green Value (0-255)
     *  @param b - Blue Value (0-255)
     *  @param a - Alpha value (0-1) (defaults to 1)
     */
    public constructor(r: Int, g: Int, b: Int, a: Float? = null)
}

public val AtomikColor.composeColor: Color
    get() = Color(
        red = r.toFloat() / 255,
        green = g.toFloat() / 255,
        blue = b.toFloat() / 255,
        alpha = this.a
    )