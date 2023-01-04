package me.kevinschildhorn.atomik.typography

import me.kevinschildhorn.atomik.typography.base.AtomikTypography
import me.kevinschildhorn.atomik.typography.base.TypographySet
import me.kevinschildhorn.atomik.typography.base.TypographyType

data class DefaultTypographySet(
    val h1: AtomikTypography? = null,
    val h2: AtomikTypography? = null,
    val h3: AtomikTypography? = null,
    val h4: AtomikTypography? = null,
    val h5: AtomikTypography? = null,
    val subtitle: AtomikTypography? = null,
    val caption: AtomikTypography? = null,
    val body: AtomikTypography,
    val button: AtomikTypography? = null,
    val footnote: AtomikTypography? = null,
) : TypographySet {
    override val defaultTypography: AtomikTypography
        get() = body

    override fun getTypography(type: TypographyType): AtomikTypography =
        when (type) {
            TypographyType.H1 -> h1 ?: defaultTypography
            TypographyType.H2 -> h2 ?: defaultTypography
            TypographyType.H3 -> h3 ?: defaultTypography
            TypographyType.H4 -> h4 ?: defaultTypography
            TypographyType.H5 -> h5 ?: defaultTypography
            TypographyType.Subtitle -> subtitle ?: defaultTypography
            TypographyType.Caption -> caption ?: defaultTypography
            TypographyType.Body -> body
            TypographyType.Button -> button ?: defaultTypography
            TypographyType.Footnote -> footnote ?: defaultTypography
            else -> body
        }
}
