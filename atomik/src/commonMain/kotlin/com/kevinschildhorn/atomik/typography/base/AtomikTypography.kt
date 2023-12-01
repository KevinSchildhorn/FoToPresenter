package com.kevinschildhorn.atomik.typography.base

/**
 * A common implementation of a Typography, that contains data for describing a font.
 *
 * @property weight the weight of the text (ex. bold, thin, normal, etc)
 * @property size the size of the text (in dp or pt)
 * @property fontName the name of the font used, if not specified it will use the default font
 */
@Suppress("SpellCheckingInspection")
public data class AtomikTypography(
    val weight: AtomikTypographyWeight = AtomikTypographyWeight.NORMAL,
    val size: Int,
    val fontName: String? = null
)
