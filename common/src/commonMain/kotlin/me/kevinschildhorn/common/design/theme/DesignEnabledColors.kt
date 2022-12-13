package me.kevinschildhorn.common.design.theme

import me.kevinschildhorn.common.design.color.SharedEnabledColor

object DesignEnabledColors {
    val default = SharedEnabledColor(
        color = DesignColors.primary,
        disabledColor = DesignColors.secondary
    )
}
