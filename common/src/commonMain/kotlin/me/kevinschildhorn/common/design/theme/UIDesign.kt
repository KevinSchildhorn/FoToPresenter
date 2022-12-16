package me.kevinschildhorn.common.design.theme

import me.kevinschildhorn.atomik.color.SharedColorSet

object UIDesign {
    fun color(light: Boolean = true): SharedColorSet =
        if (light) ColorSets.Light else ColorSets.Dark
    val typography = Typography
}
