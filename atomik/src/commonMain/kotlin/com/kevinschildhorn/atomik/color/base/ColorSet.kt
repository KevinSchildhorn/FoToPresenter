package com.kevinschildhorn.atomik.color.base

/**
 * A set of colors to be used in the project
 *
 * Is an interface that is used to hold the colors used in the project.
 * Implemented in [DefaultColorSet] and [CustomColorSet]
 *
 * @property[fallbackColor] the color that is passed if the requested color cannot be found
 */
public interface ColorSet {
    public val fallbackColor: AtomikColor

    /**
     * Gets the color based on a name
     * @param name the name of the color
     * @return the [AtomikColor] based on the name passed
     */
    public fun getColor(name: String): AtomikColor
}
