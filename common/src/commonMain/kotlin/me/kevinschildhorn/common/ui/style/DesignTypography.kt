package me.kevinschildhorn.common.ui.style

import me.kevinschildhorn.common.ui.font.SharedFont
import me.kevinschildhorn.common.ui.enums.TypeWeight

enum class DesignTypography {
    H1,
    H2,
    H3,
    H4,
    SUBTITLE,
    BUTTON,
    BODY,
    CAPTION;

    val typeWeight: TypeWeight
        get() = when (this) {
            BUTTON -> TypeWeight.BOLD
            CAPTION -> TypeWeight.MEDIUM
            else -> TypeWeight.REGULAR
        }

    val font: SharedFont
        get() = SharedFont("QuickSand")

    val fontSize: Int
        get() = when (this) {
            H1 -> 96
            H2 -> 60
            H3 -> 48
            H4 -> 34
            SUBTITLE -> 16
            BUTTON -> 14
            BODY -> 16
            CAPTION -> 12
        }
}