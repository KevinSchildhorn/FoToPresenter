package com.kevinschildhorn.atomik.typography.base

/**
 * A set of typography data to be used in the project
 *
 * Is an interface that is used to hold the typographies used in the project.
 * Implemented in [CustomTypographySet]
 *
 * @property[fallbackTypography] the Typography that is passed if the requested color cannot be found
 */
public interface TypographySet {
    public val fallbackTypography: AtomikTypography

    /**
     * Gets the typography based on a type. If none is found then the fallback is returned
     * @param type The type of typography you want to get as a [AtomikTypographyType]
     * @return the [AtomikTypography] based on the type passed
     */
    public fun getTypography(type: AtomikTypographyType): AtomikTypography
}
