package me.kevinschildhorn.common.ui.style.color

data class SharedColorStyleGuide(
    val primary: SharedColor,
    val secondary: SharedColor,
    val background: SharedColor,
    val surface: SharedColor,
    val error: SharedColor,

    val onPrimary: SharedColor,
    val onSecondary: SharedColor,
    val onBackground: SharedColor,
    val onSurface: SharedColor,
    val onError: SharedColor,
)