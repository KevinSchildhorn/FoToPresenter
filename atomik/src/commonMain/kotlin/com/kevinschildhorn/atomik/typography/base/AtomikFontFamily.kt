package com.kevinschildhorn.atomik.typography.base

import androidx.compose.ui.text.font.FontFamily

/**
 * A class containing the Font Family of the Design system
 *
 * This is either a group of UIFonts in iOS or a FontFamily in compose. It is needed to use
 * platform specific font functionality
 */
@Suppress("SpellCheckingInspection")
public expect class AtomikFontFamily {
    public val fontFamily: FontFamily
}
