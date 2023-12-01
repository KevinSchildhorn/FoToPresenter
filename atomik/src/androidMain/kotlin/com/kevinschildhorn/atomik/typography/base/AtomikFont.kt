package com.kevinschildhorn.atomik.typography.base

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight

public actual class AtomikFont(resId: Int, weight: FontWeight) {
    public val composeFont: Font = Font(resId, weight)
}