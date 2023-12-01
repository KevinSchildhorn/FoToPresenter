package com.kevinschildhorn.atomik.typography

import com.kevinschildhorn.atomik.typography.base.AtomikTypography
import com.kevinschildhorn.atomik.typography.base.AtomikTypographyType
import com.kevinschildhorn.atomik.typography.base.TypographySet

/**
 * A custom set of typography data to be used in the project
 *
 * Is an implementation of [TypographySet] that contains a map of [AtomikTypography].
 * Allows for adding Typographies with custom ids.
 * For now this acts as a wrapper to a collection of typographies
 *
 * @property fallbackTypography the Typography that is passed if the requested typography cannot be found
 * @property typographies a map of typographies that are contained in this set
 */
internal class CustomTypographySet(
    override val fallbackTypography: AtomikTypography,
    private val typographies: Map<AtomikTypographyType, AtomikTypography>
) : TypographySet {

    /**
     * Gets the typography based on a type. If none is found then the fallback is returned
     * @param type The type of typography you want to get as a [AtomikTypographyType]
     * @return the [AtomikTypography] based on the type passed
     */
    override fun getTypography(type: AtomikTypographyType): AtomikTypography =
        typographies[type] ?: fallbackTypography
}
