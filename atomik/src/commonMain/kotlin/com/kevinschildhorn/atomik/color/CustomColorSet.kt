package com.kevinschildhorn.atomik.color

import com.kevinschildhorn.atomik.color.base.AtomikColor
import com.kevinschildhorn.atomik.color.base.ColorSet

/**
 * A custom set of colors to be used in the project
 *
 * Is an implementation of [ColorSet] that contains a map of [AtomikColor].
 * Allows for adding colors with custom ids.
 * For now this acts as a wrapper to a collection of colors
 *
 * @property fallbackColor the color that is passed if the requested color cannot be found
 * @property colors a map of colors that are contained in this set
 */
public class CustomColorSet(
    override val fallbackColor: AtomikColor,
    private val colors: Map<String, AtomikColor>
) : ColorSet {

    /**
     * Gets the color based on a type. If none is found then the fallback is returned
     * @param name The name of color you want to get as a [String]
     * @return the [AtomikColor] based on the name passed
     */
    override fun getColor(name: String): AtomikColor = colors[name] ?: fallbackColor
}
