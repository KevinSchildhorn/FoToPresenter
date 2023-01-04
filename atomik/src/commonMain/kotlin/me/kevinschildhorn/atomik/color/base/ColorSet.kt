package me.kevinschildhorn.atomik.color.base

/**
 * A set of colors to be used in the project
 *
 * Is an interface that is used to hold the colors used in the project. Implemented in [DefaultColorSet] and [CustomColorSet]
 *
 * @property[defaultColor] the color that is passed if the requested color cannot be found
 */
interface ColorSet {
    val defaultColor: AtomikColor

    /**
     * Gets the color based on a name
     * @return the [AtomikColor] based on the name passed
     */
    fun getColor(name: String): AtomikColor
}
