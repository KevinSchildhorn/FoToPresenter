package me.kevinschildhorn.common.extensions

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import me.kevinschildhorn.atomik.typography.base.AtomikTypography
import me.kevinschildhorn.atomik.typography.asComposeTextStyle

fun AtomikTypography.asComposeTextStyle(fontFamily: FontFamily): TextStyle =
    this.asComposeTextStyle(fontFamily)
