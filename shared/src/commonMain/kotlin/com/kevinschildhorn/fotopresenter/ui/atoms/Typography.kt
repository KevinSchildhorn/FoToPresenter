package com.kevinschildhorn.fotopresenter.ui.atoms

import com.kevinschildhorn.atomik.typography.base.AtomikTypography
import com.kevinschildhorn.atomik.typography.base.AtomikTypographyType
import com.kevinschildhorn.atomik.typography.base.AtomikTypographyWeight



val h1 = AtomikTypography(size = 96, weight = AtomikTypographyWeight.NORMAL)
val h2 = AtomikTypography(size = 60, weight = AtomikTypographyWeight.NORMAL)
val h3 = AtomikTypography(size = 48, weight = AtomikTypographyWeight.NORMAL)
val h4 = AtomikTypography(size = 34, weight = AtomikTypographyWeight.NORMAL)
val subtitle = AtomikTypography(size = 16, weight = AtomikTypographyWeight.NORMAL)
val button = AtomikTypography(size = 14, weight = AtomikTypographyWeight.BOLD)
val body = AtomikTypography(size = 16, weight = AtomikTypographyWeight.NORMAL)
val caption = AtomikTypography(size = 14, weight = AtomikTypographyWeight.BOLD)


private fun typographyType(size: Int) = object : AtomikTypographyType {
    override val id: String
        get() = ""
    override val typography: AtomikTypography
        get() = AtomikTypography(size = size, weight = AtomikTypographyWeight.BOLD)
}