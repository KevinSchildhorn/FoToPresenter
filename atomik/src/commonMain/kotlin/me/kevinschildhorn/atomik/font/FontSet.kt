package me.kevinschildhorn.atomik.font

interface FontSet {
    val h1: SharedTypography
    val h2: SharedTypography
    val h3: SharedTypography
    val h4: SharedTypography
    val h5: SharedTypography
    val subtitle: SharedTypography
    val caption: SharedTypography
    val body: SharedTypography
    val button: SharedTypography
    val footnote: SharedTypography
}

class AndroidFontSet(fontSet: FontSet) {
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
}

class iOSFontSet(fontSet: FontSet)  {
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