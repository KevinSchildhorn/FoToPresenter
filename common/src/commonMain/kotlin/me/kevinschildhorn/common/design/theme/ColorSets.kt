package me.kevinschildhorn.common.design.theme

import me.kevinschildhorn.atomik.color.Color
import me.kevinschildhorn.atomik.color.SharedColorSet

object ColorSets {
    object Light : SharedColorSet {
        override val primary = Color(0xFFFFA500)
        override val secondary = Color(0xFFFFD383)
        override val background = Color(0xFFFFFFFF)
        override val surface = Color(0xFFFFFFFF)
        override val error = Color(0xFFFF0000)

        override val primaryText = Color(0xFF402900)
        override val secondaryText = Color(0xFFFFA500)
        override val backgroundText = Color(0xFF25231F)
        override val surfaceText = Color(0xFFFFA500)
        override val errorText = Color(0xFF9E1F1F)
    }

    object Dark : SharedColorSet {
        override val primary = Color(0xFFFFA500)
        override val secondary = Color(0xFFE8810C)
        override val background = Color(0xFF2E3034)
        override val surface = Color(0xFF55565B)
        override val error = Color(0xFFD10000)

        override val primaryText = Color(0xFF000000)
        override val secondaryText = Color(0xFF6D4E14)
        override val backgroundText = Color(0xFFB3B7C0)
        override val surfaceText = Color(0xFFFFA500)
        override val errorText = Color(0xFF5A1111)
    }
}