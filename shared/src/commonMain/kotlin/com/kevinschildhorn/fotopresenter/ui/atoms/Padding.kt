package com.kevinschildhorn.fotopresenter.ui.atoms

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

enum class Padding(val rawValue: Int) {
    LARGE(32),
    STANDARD(20),
    MEDIUM(12),
    SMALL(8),
    NONE(0),
    ;

    val dp: Dp
        get() = this.rawValue.dp
}
