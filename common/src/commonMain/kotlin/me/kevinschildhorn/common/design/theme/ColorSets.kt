package me.kevinschildhorn.common.design.theme

import me.kevinschildhorn.atomik.color.DefaultColorSet
import me.kevinschildhorn.atomik.color.base.AtomikColor

object ColorSets {
    val light = DefaultColorSet(
        primary = AtomikColor(0xFFFFA500),
        secondary = AtomikColor(0xFFFFD383),
        background = AtomikColor(0xFFFFFFFF),
        surface = AtomikColor(0xFFFFFFFF),
        error = AtomikColor(0xFFFF0000),

        primaryText = AtomikColor(0xFF402900),
        secondaryText = AtomikColor(0xFFFFA500),
        backgroundText = AtomikColor(0xFF25231F),
        surfaceText = AtomikColor(0xFFFFA500),
        errorText = AtomikColor(0xFF9E1F1F),
    )

    val dark = DefaultColorSet(
        primary = AtomikColor(0xFFFFA500),
        secondary = AtomikColor(0xFFE8810C),
        background = AtomikColor(0xFF2E3034),
        surface = AtomikColor(0xFF55565B),
        error = AtomikColor(0xFFD10000),

        primaryText = AtomikColor(0xFF000000),
        secondaryText = AtomikColor(0xFF6D4E14),
        backgroundText = AtomikColor(0xFFB3B7C0),
        surfaceText = AtomikColor(0xFFFFA500),
        errorText = AtomikColor(0xFF5A1111),
    )
}
