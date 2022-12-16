package me.kevinschildhorn.atomik.font

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp


fun SharedTypography.asComposeTextStyle(fontFamily: FontFamily) =
    TextStyle(
        fontFamily = fontFamily,
        fontWeight = this.weight.fontWeight,
        fontSize = this.size.sp,
    )