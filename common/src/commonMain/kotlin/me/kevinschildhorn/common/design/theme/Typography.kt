package me.kevinschildhorn.common.design.theme

import me.kevinschildhorn.atomik.typography.DefaultTypographySet
import me.kevinschildhorn.atomik.typography.base.AtomikTypography
import me.kevinschildhorn.atomik.typography.base.AtomikTypographyWeight

internal val sharedTypography = DefaultTypographySet(
    h1 = AtomikTypography(size = 96),
    h2 = AtomikTypography(size = 60),
    h3 = AtomikTypography(size = 48),
    h4 = AtomikTypography(size = 34),
    subtitle = AtomikTypography(size = 16),
    button = AtomikTypography(weight = AtomikTypographyWeight.BOLD, size = 14),
    body = AtomikTypography(size = 16),
    caption = AtomikTypography(weight = AtomikTypographyWeight.BOLD, size = 14),
)
