package me.kevinschildhorn.atomik.color

import me.kevinschildhorn.atomik.color.base.AtomikColor
import me.kevinschildhorn.atomik.color.base.ColorSet

class CustomColorSet(
    override val defaultColor: AtomikColor,
    private val colors: Map<String, AtomikColor>
) : ColorSet {
    override fun getColor(name: String): AtomikColor = colors[name] ?: defaultColor
}
