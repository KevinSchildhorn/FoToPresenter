package me.kevinschildhorn.atomik.typography.base

/**
 * A set of typohraphies to be used in the project
 *
 * Is an interface that is used to hold the typographies used in the project. Implemented in [PlatformTypographySet] and [CustomTypographySet] and [DefaultTypographySet]
 *
 * @property[defaultTypography] the Typography that is passed if the requested color cannot be found
 */
interface TypographySet {
    val defaultTypography: AtomikTypography

    /**
     * Gets the color based on a name
     * @param[type] The type of typograpnhy you want to get
     * @return the [AtomikTypography] based on the type passed
     */
    fun getTypography(type: TypographyType): AtomikTypography
}
