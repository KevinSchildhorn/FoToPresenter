package com.kevinschildhorn.atomik.typography.base

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font

public actual class AtomikFont(resource: String, weight: FontWeight) {
    public val composeFont: Font = Font(resource = resource, weight)
}