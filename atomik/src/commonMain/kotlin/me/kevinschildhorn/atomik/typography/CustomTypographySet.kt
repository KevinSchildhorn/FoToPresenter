package me.kevinschildhorn.atomik.typography

import me.kevinschildhorn.atomik.typography.base.AtomikTypography
import me.kevinschildhorn.atomik.typography.base.TypographySet
import me.kevinschildhorn.atomik.typography.base.TypographyType

class CustomTypographySet(
    override val defaultTypography: AtomikTypography,
    private val typographies: Map<TypographyType, AtomikTypography>
) : TypographySet {

    override fun getTypography(type: TypographyType): AtomikTypography =
        typographies[type] ?: defaultTypography
}
