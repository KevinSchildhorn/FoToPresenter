package me.kevinschildhorn.atomik.typography

import androidx.compose.ui.text.font.FontFamily
import me.kevinschildhorn.atomik.typography.base.AtomikTypography
import me.kevinschildhorn.atomik.typography.base.TypographySet
import me.kevinschildhorn.atomik.typography.base.TypographyType

actual class PlatformTypographySet actual constructor(fontSet: DefaultTypographySet) :
    TypographySet {
    val h1: AtomikTypography = fontSet.h1
    val h2: AtomikTypography = fontSet.h2
    val h3: AtomikTypography = fontSet.h3
    val h4: AtomikTypography = fontSet.h4
    val h5: AtomikTypography = fontSet.h5
    val h6: AtomikTypography = h5
    val subtitle1: AtomikTypography = fontSet.subtitle
    val subtitle2: AtomikTypography = fontSet.subtitle
    val body: AtomikTypography = fontSet.body
    val body2: AtomikTypography = body
    val button: AtomikTypography = fontSet.button
    val caption: AtomikTypography = fontSet.caption
    val overline: AtomikTypography = fontSet.footnote

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
            TypographyType.h1 -> h1
            TypographyType.h2 -> h2
            TypographyType.h3 -> h3
            TypographyType.h4 -> h4
            TypographyType.h5 -> h5
            TypographyType.h6 -> h6
            TypographyType.subtitle -> subtitle1
            TypographyType.subtitle2 -> subtitle2
            TypographyType.body -> body
            TypographyType.body2 -> body2
            TypographyType.button -> button
            TypographyType.caption -> caption
            TypographyType.overline -> overline
            else -> defaultTypography
        }
}