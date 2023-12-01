package com.kevinschildhorn.atomik.typography

import com.kevinschildhorn.atomik.typography.base.AtomikTypography
import com.kevinschildhorn.atomik.typography.base.AtomikTypographyType
import com.kevinschildhorn.atomik.typography.base.TypographySet

/**
 * A custom set of typography data to be used in the project
 *
 * Is an implementation of [TypographySet] that contains a map of [AtomikTypography].
 * Allows for adding Typographies with custom ids, that can be referenced from a custom
 * enum that is passed in. For now this acts as a wrapper to a collection of typographies
 *
 * @property fallbackTypography the Typography that is passed if the requested typography cannot be found
 * @property typographies a map of typographies that are contained in this set
 */
internal class CustomTypographySetTyped<E : Enum<*>>(
    override val fallbackTypography: AtomikTypography,
    private val typographies: Map<E, AtomikTypography>
) : TypographySet {

    /**
     * Gets the typography based on an enum value. If none is found then the fallback is returned
     * @param type The enum associated with the typography you want to get as a [AtomikTypographyType]
     * @return the [AtomikTypography] based on the type passed
     */
    public fun getTypography(type: E): AtomikTypography = typographies[type] ?: fallbackTypography

    /**
     * Gets the typography based on a type. If none is found then the fallback is returned
     * @param type The type of typography you want to get as a [AtomikTypographyType]
     * @return the [AtomikTypography] based on the type passed
     */
    override fun getTypography(type: AtomikTypographyType): AtomikTypography = fallbackTypography
}
