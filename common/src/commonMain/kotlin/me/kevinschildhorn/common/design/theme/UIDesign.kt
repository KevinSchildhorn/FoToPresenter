package me.kevinschildhorn.common.design.theme

import me.kevinschildhorn.atomik.DesignSystem
import me.kevinschildhorn.atomik.PlatformDesignSystem
import me.kevinschildhorn.atomik.color.DefaultColorSet
import me.kevinschildhorn.atomik.color.base.AtomikColor
import me.kevinschildhorn.atomik.typography.DefaultTypographySet
import me.kevinschildhorn.atomik.typography.PlatformTypographySet
import me.kevinschildhorn.atomik.typography.base.AtomikTypography
import me.kevinschildhorn.atomik.typography.base.TypographyWeight

object UIDesign {
    /*
    fun color(light: Boolean = true): DefaultColorSet =
        if (light) ColorSets.Light else ColorSets.Dark
    val typography = Typography*/
}

private val colorSet = DefaultColorSet(
    primary = AtomikColor(0xFFFFA500),
    secondary = AtomikColor(0xFFFFD383),
    background = AtomikColor(0xFFFFFFFF),
    surface = AtomikColor(0xFFFFFFFF),
    error = AtomikColor(0xFFFF0000),

    primaryText = AtomikColor(0xFF402900),
    secondaryText = AtomikColor(0xFFFFA500),
    backgroundText = AtomikColor(0xFF25231F),
    surfaceText = AtomikColor(0xFFFFA500),
    errorText = AtomikColor(0xFF9E1F1F),
)

private val typographySet = DefaultTypographySet(
    body = AtomikTypography(
        "Quicksand",
        weight = TypographyWeight.NORMAL,
        size = 16
    )
)

val designSystem = PlatformDesignSystem(
    colorSet = colorSet,
    typographySet = PlatformTypographySet(typographySet),
    atoms = emptyMap()
)