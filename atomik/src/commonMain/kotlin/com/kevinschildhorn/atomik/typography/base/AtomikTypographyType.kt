package com.kevinschildhorn.atomik.typography.base

/**
 * A wrapper for an AtomikTypography associated with an id.
 *
 * @property id the id associated with the Typography
 * @property typography the typography
 */
@Suppress("SpellCheckingInspection")
public interface AtomikTypographyType {
    public val id: String
    public val typography: AtomikTypography
}
