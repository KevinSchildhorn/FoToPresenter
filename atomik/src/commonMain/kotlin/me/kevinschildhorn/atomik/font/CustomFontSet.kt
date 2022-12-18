package me.kevinschildhorn.atomik.font

class CustomFontSet<E : Enum<E>>(
    private val fontList: MutableMap<E, SharedTypography> = mutableMapOf()
) {
    fun font(type: E) = fontList[type]
}