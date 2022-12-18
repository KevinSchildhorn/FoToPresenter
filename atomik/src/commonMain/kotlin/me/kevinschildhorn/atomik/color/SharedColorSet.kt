package me.kevinschildhorn.atomik.color

interface SharedColorSet {
    val primary: Color
    val secondary: Color
    val background: Color
    val surface: Color
    val error: Color

    val primaryText: Color
    val secondaryText: Color
    val backgroundText: Color
    val surfaceText: Color
    val errorText: Color
}
