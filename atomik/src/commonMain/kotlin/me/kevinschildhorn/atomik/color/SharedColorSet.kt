package me.kevinschildhorn.atomik.color

interface SharedColorSet {
    val primary: SharedColor
    val secondary: SharedColor
    val background: SharedColor
    val surface: SharedColor
    val error: SharedColor

    val primaryText: SharedColor
    val secondaryText: SharedColor
    val backgroundText: SharedColor
    val surfaceText: SharedColor
    val errorText: SharedColor
}
