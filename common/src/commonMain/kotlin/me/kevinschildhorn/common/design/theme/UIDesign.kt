package me.kevinschildhorn.common.design.theme

import me.kevinschildhorn.atomik.PlatformDesignSystem
import me.kevinschildhorn.atomik.typography.PlatformTypographySet

val designSystem = PlatformDesignSystem(
    colorSet = ColorSets.light,
    typographySet = PlatformTypographySet(sharedTypography),
    atoms = emptyMap(),
    fontFamily = null
)
