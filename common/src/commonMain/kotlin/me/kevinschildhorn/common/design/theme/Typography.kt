package me.kevinschildhorn.common.design.theme

import me.kevinschildhorn.atomik.typography.DefaultTypographySet
import me.kevinschildhorn.atomik.typography.base.AtomikTypography
import me.kevinschildhorn.atomik.typography.base.TypographyWeight
private val fontName = "Quicksand"
internal val sharedTypography = DefaultTypographySet(
    h1 = AtomikTypography(
        fontName,
        size = 96
    ),
    h2 = AtomikTypography(
        fontName,
        size = 60
    ),
    h3 = AtomikTypography(
        fontName,
        size = 48
    ),
    h4 = AtomikTypography(
        fontName,
        size = 34
    ),
    subtitle = AtomikTypography(
        fontName,
        size = 16
    ),
    button = AtomikTypography(
        fontName,
        weight = TypographyWeight.BOLD,
        size = 14
    ),
    body = AtomikTypography(
        fontName,
        size = 16
    ),
    caption = AtomikTypography(
        fontName,
        weight = TypographyWeight.BOLD,
        size = 14
    ),
)
