package me.kevinschildhorn.atomik.font

import androidx.compose.ui.text.font.FontWeight

val SharedWeight.fontWeight: FontWeight
    get() = when (this) {
        SharedWeight.BOLD -> FontWeight.Bold
        else -> FontWeight.Normal
    }