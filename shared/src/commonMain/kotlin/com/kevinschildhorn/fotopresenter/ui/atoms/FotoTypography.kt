package com.kevinschildhorn.fotopresenter.ui.atoms

import com.kevinschildhorn.atomik.typography.base.AtomikTypography
import com.kevinschildhorn.atomik.typography.base.AtomikTypographyWeight
import com.kevinschildhorn.fotopresenter.MR
import dev.icerock.moko.resources.FontResource

private val fontregularMoko: FontResource = MR.fonts.Quicksand.regular
private val fontBoldMoko: FontResource = MR.fonts.Quicksand.bold

object FotoTypography {
    val h1 =
        AtomikTypography(size = 96, weight = AtomikTypographyWeight.NORMAL, font = fontregularMoko)
    val h2 =
        AtomikTypography(size = 60, weight = AtomikTypographyWeight.NORMAL, font = fontregularMoko)
    val h3 =
        AtomikTypography(size = 48, weight = AtomikTypographyWeight.NORMAL, font = fontregularMoko)
    val h4 =
        AtomikTypography(size = 34, weight = AtomikTypographyWeight.NORMAL, font = fontregularMoko)
    val subtitle =
        AtomikTypography(size = 16, weight = AtomikTypographyWeight.NORMAL, font = fontregularMoko)
    val button =
        AtomikTypography(size = 14, weight = AtomikTypographyWeight.BOLD, font = fontBoldMoko)
    val body =
        AtomikTypography(size = 16, weight = AtomikTypographyWeight.NORMAL, font = fontregularMoko)
    val caption =
        AtomikTypography(size = 14, weight = AtomikTypographyWeight.BOLD, font = fontBoldMoko)
}
