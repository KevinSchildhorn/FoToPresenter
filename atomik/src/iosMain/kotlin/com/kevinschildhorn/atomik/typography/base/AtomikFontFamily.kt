package com.kevinschildhorn.atomik.typography.base

import platform.UIKit.UIFont

/**
 * A class containing the Font Family of the Design system
 *
 * This contains a map of UIFonts, based on the weight. Used to reference particular font files.
 *
 * @property uiFonts a map of UIFonts reference by [AtomikTypographyWeight]
 */
@Suppress("SpellCheckingInspection")
public class AtomikFontFamilyIOS(private val uiFonts: Map<AtomikTypographyWeight, UIFont>)
