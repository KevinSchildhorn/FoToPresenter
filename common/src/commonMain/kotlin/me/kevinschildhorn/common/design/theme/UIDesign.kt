package me.kevinschildhorn.common.design.theme

import me.kevinschildhorn.atomik.CustomDesignSystem
import me.kevinschildhorn.atomik.DefaultDesignSystem
import me.kevinschildhorn.atomik.PlatformDesignSystem
import me.kevinschildhorn.atomik.typography.CustomTypographySet
import me.kevinschildhorn.atomik.typography.PlatformTypographySet

val designSystem = DefaultDesignSystem(
    colorSet = ColorSets.light,
    typographySet = sharedTypography,
    atoms = emptyMap(),
    fontFamily = null
)
