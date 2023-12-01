package com.kevinschildhorn.fotopresenter.ui.atoms

import com.kevinschildhorn.atomik.typography.base.AtomikFont
import com.kevinschildhorn.atomik.typography.base.AtomikTypography
import com.kevinschildhorn.atomik.typography.base.AtomikTypographyWeight

expect val fontRegular:AtomikFont
expect val fontBold:AtomikFont

val h1 = AtomikTypography(size = 96, weight = AtomikTypographyWeight.NORMAL, font = fontRegular)
val h2 = AtomikTypography(size = 60, weight = AtomikTypographyWeight.NORMAL, font = fontRegular)
val h3 = AtomikTypography(size = 48, weight = AtomikTypographyWeight.NORMAL, font = fontRegular)
val h4 = AtomikTypography(size = 34, weight = AtomikTypographyWeight.NORMAL, font = fontRegular)
val subtitle = AtomikTypography(size = 16, weight = AtomikTypographyWeight.NORMAL, font = fontRegular)
val button = AtomikTypography(size = 14, weight = AtomikTypographyWeight.BOLD, font = fontBold)
val body = AtomikTypography(size = 16, weight = AtomikTypographyWeight.NORMAL, font = fontRegular)
val caption = AtomikTypography(size = 14, weight = AtomikTypographyWeight.BOLD, font = fontBold)