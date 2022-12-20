package me.kevinschildhorn.atomik.typography

import me.kevinschildhorn.atomik.typography.base.AtomikTypography
import me.kevinschildhorn.atomik.typography.base.TypographySet
import me.kevinschildhorn.atomik.typography.base.TypographyType

@Suppress("unused", "usedFromiOS")
actual class PlatformTypographySet actual constructor(typographySet: DefaultTypographySet) :
    TypographySet {
    val largeTitle: AtomikTypography = typographySet.h1 ?: typographySet.defaultTypography
    val title: AtomikTypography = typographySet.h2 ?: typographySet.defaultTypography
    val title2: AtomikTypography = typographySet.h3 ?: typographySet.defaultTypography
    val title3: AtomikTypography = typographySet.h4 ?: typographySet.defaultTypography
    val headline: AtomikTypography = typographySet.h5 ?: typographySet.defaultTypography
    val subheadline: AtomikTypography = typographySet.subtitle ?: typographySet.defaultTypography
    val body: AtomikTypography = typographySet.body
    val callout: AtomikTypography = typographySet.button ?: typographySet.defaultTypography
    val caption: AtomikTypography = typographySet.caption ?: typographySet.defaultTypography
    val caption2: AtomikTypography = caption
    val footnote: AtomikTypography = typographySet.footnote ?: typographySet.defaultTypography

    override val defaultTypography: AtomikTypography
        get() = body

    override fun getTypography(type: TypographyType): AtomikTypography =
        when (type) {
            TypographyType.LargeTitle -> largeTitle
            TypographyType.Title -> title
            TypographyType.Title2 -> title2
            TypographyType.Title3 -> title3
            TypographyType.Headline -> headline
            TypographyType.Subheadline -> subheadline
            TypographyType.Body -> body
            TypographyType.Callout -> callout
            TypographyType.Caption -> caption
            TypographyType.Caption2 -> caption2
            TypographyType.Footnote -> footnote
            else -> defaultTypography
        }
}
