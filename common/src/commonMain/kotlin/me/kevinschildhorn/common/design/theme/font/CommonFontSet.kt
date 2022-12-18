package me.kevinschildhorn.common.design.theme.font

import me.kevinschildhorn.atomik.typography.CustomTypographySet
import me.kevinschildhorn.atomik.typography.base.AtomikTypography
import me.kevinschildhorn.atomik.typography.base.TypographyWeight


enum class FontTypes {
    Button,
    Text
}
/*
val customFontSet: CustomTypographySet
    get() {
        val builder = FontSetBuilder<FontTypes>()
        builder.addFont(FontTypes.Button,
            AtomikTypography("Hello", TypographyWeight.NORMAL, 15)
        )
        builder.addFont(FontTypes.Text,
            AtomikTypography("Hello", TypographyWeight.NORMAL, 16)
        )
        return builder.build()
    }*/