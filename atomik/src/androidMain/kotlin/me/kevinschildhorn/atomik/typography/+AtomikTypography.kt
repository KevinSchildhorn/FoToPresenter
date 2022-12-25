package me.kevinschildhorn.atomik.typography

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import me.kevinschildhorn.atomik.typography.base.AtomikTypography

fun AtomikTypography.asComposeTextStyle(fontFamily: FontFamily): TextStyle =
    TextStyle(
        fontFamily = fontFamily,
        fontWeight = this.weight.fontWeight,
        fontSize = this.size.sp,
    )
