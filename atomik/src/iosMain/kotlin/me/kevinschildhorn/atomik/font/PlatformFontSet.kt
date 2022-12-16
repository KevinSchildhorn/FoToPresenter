package me.kevinschildhorn.atomik.font

actual class PlatformFontSet actual constructor(fontSet: FontSet)  {
    val largeTitle: SharedTypography = fontSet.h1
    val title: SharedTypography = fontSet.h2
    val title2: SharedTypography = fontSet.h3
    val title3: SharedTypography = fontSet.h4
    val headline: SharedTypography = fontSet.h5
    val subheadline: SharedTypography = fontSet.subtitle
    val body: SharedTypography = fontSet.body
    val callout: SharedTypography = fontSet.button
    val caption: SharedTypography = fontSet.caption
    val caption2: SharedTypography = caption
    val footnote: SharedTypography = fontSet.footnote
}