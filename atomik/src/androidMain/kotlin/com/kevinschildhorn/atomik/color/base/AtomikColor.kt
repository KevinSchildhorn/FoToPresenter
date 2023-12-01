package com.kevinschildhorn.atomik.color.base

import androidx.compose.ui.graphics.Color

/*
 * Android implementation of [AtomikColor]
 *
 * @property viewColor the AtomikColor as an android graphics Color [android.graphics.Color]
 * (https://developer.android.com/reference/android/graphics/Color#parseColor(java.lang.String))
 * Format is either #RRGGBB or #AARRGGBB
 * @property composeColor the AtomikColor as a compose Color [androidx.compose.ui.graphics.Color]
 * hex code for color (#AARRGGBB) (alpha optional)
 * @property hexString The hex value of the color (#AARRGGBB)
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

    /*
     * hex code for color (#AARRGGBB) (alpha optional)
     */
    private val androidHexColor: String
    public val viewColor: Int
        get() = android.graphics.Color.parseColor(androidHexColor)

    public actual constructor(hex: Long) {
        val data = AtomikColorData(hex)
        this.hexString = data.hexString
        this.androidHexColor = data.hexStringAlphaFirst
        this.r = data.r
        this.g = data.g
        this.b = data.b
        this.a = data.a
    }

    public actual constructor(r: Int, g: Int, b: Int, a: Float?) {
        val data = AtomikColorData(r, g, b, a)
        this.hexString = data.hexString
        this.androidHexColor = data.hexStringAlphaFirst
        this.r = data.r
        this.g = data.g
        this.b = data.b
        this.a = data.a
    }
}
