package me.kevinschildhorn.atomik.typography

import me.kevinschildhorn.atomik.typography.base.AtomikTypography
import me.kevinschildhorn.atomik.typography.base.TypographySet
import me.kevinschildhorn.atomik.typography.base.TypographyType

@Suppress("unused", "usedFromiOS")
actual class PlatformTypographySet actual constructor(fontSet: DefaultTypographySet) :
    TypographySet {
    val largeTitle: AtomikTypography = fontSet.h1 ?: fontSet.defaultTypography
    val title: AtomikTypography = fontSet.h2 ?: fontSet.defaultTypography
    val title2: AtomikTypography = fontSet.h3 ?: fontSet.defaultTypography
    val title3: AtomikTypography = fontSet.h4 ?: fontSet.defaultTypography
    val headline: AtomikTypography = fontSet.h5 ?: fontSet.defaultTypography
    val subheadline: AtomikTypography = fontSet.subtitle ?: fontSet.defaultTypography
    val body: AtomikTypography = fontSet.body
    val callout: AtomikTypography = fontSet.button ?: fontSet.defaultTypography
    val caption: AtomikTypography = fontSet.caption ?: fontSet.defaultTypography
    val caption2: AtomikTypography = caption ?: fontSet.defaultTypography
    val footnote: AtomikTypography = fontSet.footnote ?: fontSet.defaultTypography

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