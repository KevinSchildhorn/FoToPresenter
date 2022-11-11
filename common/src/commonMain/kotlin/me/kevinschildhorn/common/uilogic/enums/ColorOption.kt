package me.kevinschildhorn.common.uilogic.enums

import me.kevinschildhorn.common.color.SharedColor
import me.kevinschildhorn.common.theme.DesignColors

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
