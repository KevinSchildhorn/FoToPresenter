package me.kevinschildhorn.atomik.font

class FontSetBuilder<E : Enum<E>>() {

    private val fontList: MutableMap<E, SharedTypography> = mutableMapOf()

    fun addFont(type: E, font: SharedTypography){
        fontList[type] = (font)
    }

    fun build(): CustomFontSet<E> {
        return CustomFontSet(fontList)
    }
}