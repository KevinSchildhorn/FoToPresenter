package com.kevinschildhorn.atomik

import com.kevinschildhorn.atomik.color.base.AtomikColor
import kotlin.test.Test
import kotlin.test.assertEquals

class AtomikColorTest {

    data class ColorTest(
        val hexColor: Long,
        val hexColorString: String,
        val r: Int,
        val g: Int,
        val b: Int,
        val a: Float = 1F
    )

    private val colorTestNoAlpha = ColorTest(
        hexColor = 0xa63028,
        hexColorString = "#A63028",
        r = 166,
        g = 48,
        b = 40
    )

    private val colorTestAlpha = ColorTest(
        hexColor = 0xa63028E6,
        hexColorString = "#A63028E6",
        r = 166,
        g = 48,
        b = 40,
        a = 0.90F
    )

    private val colorWhite = ColorTest(
        hexColor = 0xffffffff,
        hexColorString = "#FFFFFFFF",
        r = 255,
        g = 255,
        b = 255,
        a = 1F
    )

    @Test
    fun testColorRGB() {
        val color = AtomikColor(colorTestNoAlpha.r, colorTestNoAlpha.g, colorTestNoAlpha.b)
        assertEquals(expected = colorTestNoAlpha.a, actual = color.a)
        assertEquals(expected = colorTestNoAlpha.r, actual = color.r)
        assertEquals(expected = colorTestNoAlpha.g, actual = color.g)
        assertEquals(expected = colorTestNoAlpha.b, actual = color.b)
        assertEquals(expected = colorTestNoAlpha.hexColorString, actual = color.hexString)
        assertAll(colorTestNoAlpha, color)
    }

    @Test
    fun testColorRGBA() {
        val color = AtomikColor(
            colorTestAlpha.r,
            colorTestAlpha.g,
            colorTestAlpha.b,
            colorTestAlpha.a
        )
        assertAll(colorTestAlpha, color)
    }

    @Test
    fun testColorRGBAFull() {
        val color = AtomikColor(
            colorWhite.r,
            colorWhite.g,
            colorWhite.b,
            colorWhite.a
        )
        assertAll(colorWhite, color)
    }

    @Test
    fun testColorHex() {
        val color = AtomikColor(colorTestNoAlpha.hexColor)
        assertAll(colorTestNoAlpha, color)
    }

    @Test
    fun testColorHexWithAlpha() {
        val color = AtomikColor(colorTestAlpha.hexColor)
        assertAll(colorTestAlpha, color)
    }

    private fun assertAll(ColorTest: ColorTest, color: AtomikColor) {
        assertEquals(expected = ColorTest.a, actual = color.a)
        assertEquals(expected = ColorTest.r, actual = color.r)
        assertEquals(expected = ColorTest.g, actual = color.g)
        assertEquals(expected = ColorTest.b, actual = color.b)
        assertEquals(expected = ColorTest.hexColorString, actual = color.hexString)
    }
}
