package com.kevinschildhorn.fotopresenter.ui.atoms

import com.kevinschildhorn.atomik.color.CustomColorSet
import com.kevinschildhorn.atomik.color.base.AtomikColor

val primary = AtomikColor(0xFFA500)
val background = AtomikColor(0xFFFFFF)
val surface = AtomikColor(0xFFFFFF)
val error = AtomikColor(0xFF0000)
val primaryText = AtomikColor(0x402900)
val secondary = AtomikColor(0xFFD383)
val secondaryText = AtomikColor(0xFFA500)
val backgroundText = AtomikColor(0x25231F)
val surfaceText = AtomikColor(0xFFA500)
val errorText = AtomikColor(0x9E1F1F)
val disabled = AtomikColor(0xE0E0E0)


val sampleColorSet = CustomColorSet(
    fallbackColor = primary,
    colors = mapOf(
        "primary" to primary,
        "background" to AtomikColor(0xFFFFFF),
        "surface" to AtomikColor(0xFFFFFF),
        "error" to AtomikColor(0xFF0000),
        "primaryText" to AtomikColor(0x402900),
        "secondary" to AtomikColor(0xFFD383),
        "secondaryText" to AtomikColor(0xFFA500),
        "backgroundText" to AtomikColor(0x25231F),
        "surfaceText" to AtomikColor(0xFFA500),
        "errorText" to AtomikColor(0x9E1F1F),
    )
)