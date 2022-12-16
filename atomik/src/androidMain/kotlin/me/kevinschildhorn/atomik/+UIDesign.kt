package me.kevinschildhorn.atomik

import androidx.compose.material.Colors
import androidx.compose.material.Typography
import androidx.compose.ui.text.font.FontFamily
import me.kevinschildhorn.common.design.theme.UIDesign


fun UIDesign.composeTypography(fontFamily: FontFamily) = Typography(
    h1 = typography.h1.asComposeTextStyle(fontFamily),
    h2 = typography.h2.asComposeTextStyle(fontFamily),
    h3 = typography.h3.asComposeTextStyle(fontFamily),
    h4 = typography.h4.asComposeTextStyle(fontFamily),
    subtitle1 = typography.subtitle.asComposeTextStyle(fontFamily),
    button = typography.button.asComposeTextStyle(fontFamily),
    body1 = typography.body.asComposeTextStyle(fontFamily),
    caption = typography.caption.asComposeTextStyle(fontFamily),
)

fun UIDesign.composeColors(darkTheme: Boolean): Colors {
    val colors = this.color(darkTheme)
    return Colors(
        primary = colors.primary.androidColor,
        primaryVariant = colors.primary.androidColor,
        secondary = colors.secondary.androidColor,
        secondaryVariant = colors.secondary.androidColor,
        background = colors.background.androidColor,
        surface = colors.surface.androidColor,
        error = colors.error.androidColor,
        onPrimary = colors.primaryText.androidColor,
        onSecondary = colors.secondaryText.androidColor,
        onBackground = colors.backgroundText.androidColor,
        onSurface = colors.surfaceText.androidColor,
        onError = colors.errorText.androidColor,
        isLight = !darkTheme,
    )
}