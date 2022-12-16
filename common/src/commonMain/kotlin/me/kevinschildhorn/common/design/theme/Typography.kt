package me.kevinschildhorn.common.design.theme

import me.kevinschildhorn.common.design.atomicLib.font.SharedTypography
import me.kevinschildhorn.common.design.atomicLib.font.SharedWeight

object Typography {
    private const val fontName = "Quicksand"

    val h1 = SharedTypography(fontName, weight = SharedWeight.NORMAL, size = 96)
    val h2 = SharedTypography(fontName, weight = SharedWeight.NORMAL, size = 60)
    val h3 = SharedTypography(fontName, weight = SharedWeight.NORMAL, size = 48)
    val h4 = SharedTypography(fontName, weight = SharedWeight.NORMAL, size = 34)
    val subtitle = SharedTypography(fontName, weight = SharedWeight.NORMAL, size = 16)
    val button = SharedTypography(fontName, weight = SharedWeight.BOLD, size = 14)
    val body = SharedTypography(fontName, weight = SharedWeight.NORMAL, size = 16)
    val caption = SharedTypography(fontName, weight = SharedWeight.BOLD, size = 14)
}