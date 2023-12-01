package com.kevinschildhorn.atomik.color.base

import kotlin.math.roundToInt

/**
 * A common collection of Color Data
 *
 * This is used to share logic to create [AtomikColor], while using platform specific
 * implementations of [AtomikColor]
 *
 * @param hexString the hex value of the color as a string, with the alpha at the end (format: #RRGGBBAA)
 * @param hexStringAlphaFirst the hex value of the color as a string, with the alpha at the beginning (format: #AARRGGBB)
 * @param r The red Value (0-255)
 * @param g The red Value (0-255)
 * @param b The red Value (0-255)
 * @param a The alpha Value (0-1) (Defaults to 1)
 */
@Suppress("SpellCheckingInspection")
internal data class AtomikColorData(
    val hexString: String,
    val hexStringAlphaFirst: String = hexString,
    val r: Int,
    val g: Int,
    val b: Int,
    val a: Float
) {

    /**
     * @constructor constructor with hex value
     * @param hex hex value
     **/
    constructor(hex: Long) : this(
        hexString = hex.asHexString,
        hexStringAlphaFirst = hex.asHexString,
        r = hex.red,
        g = hex.green,
        b = hex.blue,
        a = hex.alpha
    )

    /**
     * @constructor constructor with rgba value
     * @param r red value (0-255)
     * @param g green value (0-255)
     * @param b blue value (0-255)
     * @param a alpha value (0-1) (Defaults to 1)
     **/
    constructor(r: Int, g: Int, b: Int, a: Float? = null) : this(
        hexString = hexFromRGB(r, g, b, a, false),
        hexStringAlphaFirst = hexFromRGB(r, g, b, a, true),
        r = r,
        g = g,
        b = b,
        a = a ?: 1F
    )
}

// Hex String to RGBA

private val Long.asHexString: String
    get() = "#${this.toString(16)}".uppercase()

private val Long.red: Int
    get() = if (this.asHexString.isLongFormHex) (this and 0xFF000000 shr 24).toInt() else (this and 0xFF0000 shr 16).toInt()

private val Long.green: Int
    get() = if (this.asHexString.isLongFormHex) (this and 0xFF0000 shr 16).toInt() else (this and 0xFF00 shr 8).toInt()

private val Long.blue: Int
    get() = if (this.asHexString.isLongFormHex) (this and 0xFF00 shr 8).toInt() else (this and 0xFF).toInt()

private val Long.alpha: Float
    get() = if (this.asHexString.isLongFormHex) ((this and 0xFF).toFloat() / 255).rounded else 1F

private val String.isLongFormHex: Boolean
    get() = this.length == 8 + 1

private fun hexFromRGB(r: Int, g: Int, b: Int, a: Float?, alphaIsPrefix: Boolean): String {
    val rgbString = (r.asHexString + g.asHexString + b.asHexString)
    return if (alphaIsPrefix) {
        "#${a.asAlphaString + rgbString}".uppercase()
    } else {
        "#${rgbString + a.asAlphaString}".uppercase()
    }
}

// RGBA to String

private val Float.rounded: Float
    get() = (this * 100).toInt() / 100F

private val Int.asHexString: String
    get() = toString(16).padStart(2, '0')

private val Float?.asAlphaString: String
    get() = this?.let { (it * 255).roundToInt().asHexString } ?: ""
