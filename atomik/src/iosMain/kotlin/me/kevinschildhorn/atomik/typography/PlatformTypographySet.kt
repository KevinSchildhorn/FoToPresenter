package me.kevinschildhorn.atomik.typography

import me.kevinschildhorn.atomik.typography.base.AtomikTypography
import me.kevinschildhorn.atomik.typography.base.TypographySet
import me.kevinschildhorn.atomik.typography.base.TypographyType

actual class PlatformTypographySet actual constructor(fontSet: DefaultTypographySet) :
    TypographySet {
    val largeTitle: AtomikTypography = fontSet.h1
    val title: AtomikTypography = fontSet.h2
    val title2: AtomikTypography = fontSet.h3
    val title3: AtomikTypography = fontSet.h4
    val headline: AtomikTypography = fontSet.h5
    val subheadline: AtomikTypography = fontSet.subtitle
    val body: AtomikTypography = fontSet.body
    val callout: AtomikTypography = fontSet.button
    val caption: AtomikTypography = fontSet.caption
    val caption2: AtomikTypography = caption
    val footnote: AtomikTypography = fontSet.footnote

    override val defaultTypography: AtomikTypography
        get() = body

    override fun getTypography(type: TypographyType): AtomikTypography {
        when (type) {
            TypographyType.largeTitle -> largeTitle
            TypographyType.title -> title
            TypographyType.title2 -> title2
            TypographyType.title3 -> title3
            TypographyType.headline -> headline
            TypographyType.subheadline -> subheadline
            TypographyType.body -> body
            TypographyType.callout -> callout
            TypographyType.caption -> caption
            TypographyType.caption2 -> caption2
            TypographyType.footnote -> footnote
            else -> defaultTypography
        }
    }
}