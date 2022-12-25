package me.kevinschildhorn.atomik.typography

import androidx.compose.ui.text.font.FontWeight
import me.kevinschildhorn.atomik.typography.base.TypographyWeight

val TypographyWeight.fontWeight: FontWeight
    get() = when (this) {
        TypographyWeight.BOLD -> FontWeight.Bold
        else -> FontWeight.Normal
    }
