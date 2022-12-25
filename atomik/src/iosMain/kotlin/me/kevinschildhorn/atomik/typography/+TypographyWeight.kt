package me.kevinschildhorn.atomik.typography

import me.kevinschildhorn.atomik.typography.base.TypographyWeight
import platform.UIKit.UIFontWeight
import platform.UIKit.UIFontWeightBold
import platform.UIKit.UIFontWeightRegular

val TypographyWeight.fontWeight: UIFontWeight
    get() = when (this) {
        TypographyWeight.NORMAL -> UIFontWeightRegular
        TypographyWeight.BOLD -> UIFontWeightBold
        TypographyWeight.ITALIC -> UIFontWeightRegular
    }