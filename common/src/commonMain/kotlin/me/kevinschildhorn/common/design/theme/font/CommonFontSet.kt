package me.kevinschildhorn.common.design.theme.font

import me.kevinschildhorn.atomik.font.CustomFontSet
import me.kevinschildhorn.atomik.font.FontSetBuilder
import me.kevinschildhorn.atomik.font.SharedTypography
import me.kevinschildhorn.atomik.font.SharedWeight


enum class FontTypes {
    Button,
    Text
}

val customFontSet: CustomFontSet<FontTypes>
    get() {
        val builder = FontSetBuilder<FontTypes>()
        builder.addFont(FontTypes.Button, SharedTypography("Hello", SharedWeight.NORMAL, 15))
        builder.addFont(FontTypes.Text, SharedTypography("Hello", SharedWeight.NORMAL, 16))
        return builder.build()
    }