package me.kevinschildhorn.common.uilogic.enums

import me.kevinschildhorn.common.theme.DesignColors
import me.kevinschildhorn.common.ui.SharedColor


enum class ColorOption {
    NORMAL,
    ERROR,
    HINT,
    SUCCESS
}

enum class SharedColorOption(val sharedColor: SharedColor) {
    NORMAL(DesignColors.primary),
    ERROR(DesignColors.error),
    HINT(DesignColors.hint),
    SUCCESS(DesignColors.success)
}