package com.kevinschildhorn.atomik.typography.base

import androidx.compose.ui.text.font.FontFamily
import platform.UIKit.UIFont

/**
 * A class containing the Font Family of the Design system
 *
 * This contains a map of UIFonts, based on the weight. Used to reference particular font files.
 *
 * @property uiFonts a map of UIFonts reference by [AtomikTypographyWeight]
 */
@Suppress("SpellCheckingInspection")
public actual class AtomikFontFamily(private val uiFonts: Map<AtomikTypographyWeight, UIFont>){
    public actual val fontFamily: FontFamily = TODO()
}
