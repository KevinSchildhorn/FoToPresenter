package me.kevinschildhorn.atomik.font

import androidx.compose.material.Typography
import androidx.compose.ui.text.font.FontFamily

actual class PlatformFontSet actual constructor(fontSet: FontSet) {
    val h1: SharedTypography = fontSet.h1
    val h2: SharedTypography = fontSet.h2
    val h3: SharedTypography = fontSet.h3
    val h4: SharedTypography = fontSet.h4
    val h5: SharedTypography = fontSet.h5
    val h6: SharedTypography = h5
    val subtitle1: SharedTypography = fontSet.subtitle
    val subtitle2: SharedTypography = fontSet.subtitle
    val body: SharedTypography = fontSet.body
    val body2: SharedTypography = body
    val button: SharedTypography = fontSet.button
    val caption: SharedTypography = fontSet.caption
    val overline: SharedTypography = fontSet.footnote

    fun asComposeTypography(fontFamily: FontFamily) = Typography(
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
}