package me.kevinschildhorn.common.design.theme

import me.kevinschildhorn.atomik.typography.base.AtomikTypography
import me.kevinschildhorn.atomik.typography.base.TypographyWeight

object Typography {
    private const val fontName = "Quicksand"

    val h1 = AtomikTypography(
        fontName,
        weight = TypographyWeight.NORMAL,
        size = 96
    )
    val h2 = AtomikTypography(
        fontName,
        weight = TypographyWeight.NORMAL,
        size = 60
    )
    val h3 = AtomikTypography(
        fontName,
        weight = TypographyWeight.NORMAL,
        size = 48
    )
    val h4 = AtomikTypography(
        fontName,
        weight = TypographyWeight.NORMAL,
        size = 34
    )
    val subtitle = AtomikTypography(
        fontName,
        weight = TypographyWeight.NORMAL,
        size = 16
    )
    val button = AtomikTypography(
        fontName,
        weight = TypographyWeight.BOLD,
        size = 14
    )
    val body = AtomikTypography(
        fontName,
        weight = TypographyWeight.NORMAL,
        size = 16
    )
    val caption = AtomikTypography(
        fontName,
        weight = TypographyWeight.BOLD,
        size = 14
    )
}