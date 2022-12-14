package me.kevinschildhorn.common.design.theme

import me.kevinschildhorn.common.design.atomicLib.color.SharedColor
import me.kevinschildhorn.common.design.atomicLib.color.SharedColorSet
import me.kevinschildhorn.common.design.atomicLib.font.SharedTypography
import me.kevinschildhorn.common.design.atomicLib.font.Weight

object Design {
    fun color(light: Boolean = true): SharedColorSet = if (light) Light else Dark

    object Typography {
        private const val fontName = "Quicksand"

        val h1 = SharedTypography(fontName, weight = Weight.NORMAL, size = 96)
        val h2 = SharedTypography(fontName, weight = Weight.NORMAL,  size = 60)
        val h3 = SharedTypography(fontName, weight = Weight.NORMAL,  size = 48)
        val h4 = SharedTypography(fontName, weight = Weight.NORMAL,  size = 34)
        val subtitle = SharedTypography(fontName, weight = Weight.NORMAL,  size = 16)
        val button = SharedTypography(fontName, weight = Weight.BOLD, size = 14)
        val body = SharedTypography(fontName, weight = Weight.NORMAL,  size = 16)
        val caption = SharedTypography(fontName, weight = Weight.BOLD, size = 14)
    }

    //region colors

    private object Light : SharedColorSet {
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

    private object Dark : SharedColorSet {
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

    //endregion
}