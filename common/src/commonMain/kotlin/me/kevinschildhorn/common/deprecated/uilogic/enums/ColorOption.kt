package me.kevinschildhorn.common.deprecated.uilogic.enums

import me.kevinschildhorn.common.design.color.SharedColor
import me.kevinschildhorn.common.design.theme.DesignColors

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
