package me.kevinschildhorn.atomik.typography.base

interface TypographySet {
    val defaultTypography: AtomikTypography
    fun getTypography(type: TypographyType): AtomikTypography
}
