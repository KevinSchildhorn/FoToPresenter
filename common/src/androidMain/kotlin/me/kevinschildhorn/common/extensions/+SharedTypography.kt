package me.kevinschildhorn.common.extensions

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import me.kevinschildhorn.atomik.font.SharedTypography
import me.kevinschildhorn.atomik.font.asComposeTextStyle

fun SharedTypography.asComposeTextStyle(fontFamily: FontFamily): TextStyle =
    this.asComposeTextStyle(fontFamily)
