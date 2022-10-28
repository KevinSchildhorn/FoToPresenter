package me.kevinschildhorn.common.ui.style

import me.kevinschildhorn.common.ui.style.color.SharedColor
import me.kevinschildhorn.common.ui.style.color.SharedColorStyleGuide

object DesignColors {

    val light = SharedColorStyleGuide(
        primary = SharedColor(0xFFFFA500),
        secondary = SharedColor(0xFFFFD383),
        background = SharedColor(0xFFFFFFFF),
        surface = SharedColor(0xFFFFFFFF),
        error = SharedColor(0xFFFF0000),

        onPrimary = SharedColor(0xFF402900),
        onSecondary = SharedColor(0xFFFFA500),
        onBackground = SharedColor(0xFF25231F),
        onSurface = SharedColor(0xFFFFA500),
        onError = SharedColor(0xFF9E1F1F),
    )

    val dark = SharedColorStyleGuide(
        primary = SharedColor(0xFFFFA500),
        secondary = SharedColor(0xFFE8810C),
        background = SharedColor(0xFF2E3034),
        surface = SharedColor(0xFF55565B),
        error = SharedColor(0xFFD10000),

        onPrimary = SharedColor(0xFF000000),
        onSecondary = SharedColor(0xFF6D4E14),
        onBackground = SharedColor(0xFFB3B7C0),
        onSurface = SharedColor(0xFFFFA500),
        onError = SharedColor(0xFF5A1111),
    )
}