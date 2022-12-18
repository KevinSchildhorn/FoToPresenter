package me.kevinschildhorn.atomik.typography

import me.kevinschildhorn.atomik.typography.base.AtomikTypography
import me.kevinschildhorn.atomik.typography.base.TypographySet
import me.kevinschildhorn.atomik.typography.base.TypographyType

data class DefaultTypographySet(
    val h1: AtomikTypography,
    val h2: AtomikTypography,
    val h3: AtomikTypography,
    val h4: AtomikTypography,
    val h5: AtomikTypography,
    val subtitle: AtomikTypography,
    val caption: AtomikTypography,
    val body: AtomikTypography,
    val button: AtomikTypography,
    val footnote: AtomikTypography,
) : TypographySet {
    override val defaultTypography: AtomikTypography
        get() = body

    override fun getTypography(type: TypographyType): AtomikTypography =
        when (type) {
            TypographyType.h1 -> h1
            TypographyType.h2 -> h2
            TypographyType.h3 -> h3
            TypographyType.h4 -> h4
            TypographyType.h5 -> h5
            TypographyType.subtitle -> subtitle
            TypographyType.caption -> caption
            TypographyType.body -> body
            TypographyType.button -> button
            TypographyType.footnote -> footnote
            else -> body
        }
}