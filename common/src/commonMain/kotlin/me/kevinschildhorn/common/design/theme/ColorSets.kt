package me.kevinschildhorn.common.design.theme

import me.kevinschildhorn.atomik.color.SharedColor
import me.kevinschildhorn.atomik.color.SharedColorSet

object ColorSets {
    object Light : SharedColorSet {
        override val primary = SharedColor(0xFFFFA500)
        override val secondary = SharedColor(0xFFFFD383)
        override val background = SharedColor(0xFFFFFFFF)
        override val surface = SharedColor(0xFFFFFFFF)
        override val error = SharedColor(0xFFFF0000)

        override val primaryText = SharedColor(0xFF402900)
        override val secondaryText = SharedColor(0xFFFFA500)
        override val backgroundText = SharedColor(0xFF25231F)
        override val surfaceText = SharedColor(0xFFFFA500)
        override val errorText = SharedColor(0xFF9E1F1F)
    }

    object Dark : SharedColorSet {
        override val primary = SharedColor(0xFFFFA500)
        override val secondary = SharedColor(0xFFE8810C)
        override val background = SharedColor(0xFF2E3034)
        override val surface = SharedColor(0xFF55565B)
        override val error = SharedColor(0xFFD10000)

        override val primaryText = SharedColor(0xFF000000)
        override val secondaryText = SharedColor(0xFF6D4E14)
        override val backgroundText = SharedColor(0xFFB3B7C0)
        override val surfaceText = SharedColor(0xFFFFA500)
        override val errorText = SharedColor(0xFF5A1111)
    }
}