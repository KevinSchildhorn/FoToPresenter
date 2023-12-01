// ktlint-disable filename
package com.kevinschildhorn.atomik.typography

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.kevinschildhorn.atomik.typography.base.AtomikTypographyWeight

/**
 * A composable [FontWeight] based on the [AtomikTypographyWeight]
 */
internal val AtomikTypographyWeight.fontWeight: FontWeight
    get() = when (this) {
        AtomikTypographyWeight.BOLD -> FontWeight.Bold
        else -> FontWeight.Normal
    }
