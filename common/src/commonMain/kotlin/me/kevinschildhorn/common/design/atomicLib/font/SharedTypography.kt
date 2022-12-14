package me.kevinschildhorn.common.design.atomicLib.font

enum class Weight {
    NORMAL,
    BOLD,
    ITALIC
}

data class SharedTypography(val fontName: String, val weight: Weight, val size: Int)