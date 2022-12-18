package me.kevinschildhorn.atomik.typography

import androidx.compose.ui.text.font.FontFamily
import me.kevinschildhorn.atomik.typography.base.AtomikTypography
import me.kevinschildhorn.atomik.typography.base.TypographySet
import me.kevinschildhorn.atomik.typography.base.TypographyType

actual class PlatformTypographySet actual constructor(fontSet: DefaultTypographySet) :
    TypographySet {
    val h1: AtomikTypography = fontSet.h1 ?: fontSet.defaultTypography
    val h2: AtomikTypography = fontSet.h2 ?: fontSet.defaultTypography
    val h3: AtomikTypography = fontSet.h3 ?: fontSet.defaultTypography
    val h4: AtomikTypography = fontSet.h4 ?: fontSet.defaultTypography
    val h5: AtomikTypography = fontSet.h5 ?: fontSet.defaultTypography
    val h6: AtomikTypography = h5
    val subtitle1: AtomikTypography = fontSet.subtitle ?: fontSet.defaultTypography
    val subtitle2: AtomikTypography = fontSet.subtitle ?: fontSet.defaultTypography
    val body: AtomikTypography = fontSet.body
    val body2: AtomikTypography = body
    val button: AtomikTypography = fontSet.button ?: fontSet.defaultTypography
    val caption: AtomikTypography = fontSet.caption ?: fontSet.defaultTypography
    val overline: AtomikTypography = fontSet.footnote ?: fontSet.defaultTypography

    fun asComposeTypography(fontFamily: FontFamily) = androidx.compose.material.Typography(
        h1 = h1.asComposeTextStyle(fontFamily),
        h2 = h2.asComposeTextStyle(fontFamily),
        h3 = h3.asComposeTextStyle(fontFamily),
        h4 = h4.asComposeTextStyle(fontFamily),
        h5 = h5.asComposeTextStyle(fontFamily),
        h6 = h6.asComposeTextStyle(fontFamily),
        subtitle1 = subtitle1.asComposeTextStyle(fontFamily),
        subtitle2 = subtitle2.asComposeTextStyle(fontFamily),
        body1 = body.asComposeTextStyle(fontFamily),
        body2 = body2.asComposeTextStyle(fontFamily),
        button = button.asComposeTextStyle(fontFamily),
        caption = caption.asComposeTextStyle(fontFamily),
        overline = overline.asComposeTextStyle(fontFamily),
    )

    override val defaultTypography: AtomikTypography
        get() = body

    override fun getTypography(type: TypographyType): AtomikTypography =
        when (type) {
            TypographyType.H1 -> h1
            TypographyType.H2 -> h2
            TypographyType.H3 -> h3
            TypographyType.H4 -> h4
            TypographyType.H5 -> h5
            TypographyType.H6 -> h6
            TypographyType.Subtitle -> subtitle1
            TypographyType.Subtitle2 -> subtitle2
            TypographyType.Body -> body
            TypographyType.Body2 -> body2
            TypographyType.Button -> button
            TypographyType.Caption -> caption
            TypographyType.Overline -> overline
            else -> defaultTypography
        }
}