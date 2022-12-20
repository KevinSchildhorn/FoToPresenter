package me.kevinschildhorn.atomik.typography.base

data class AtomikTypography(
    val fontName: String,
    val weight: TypographyWeight = TypographyWeight.NORMAL,
    val size: Int
)
