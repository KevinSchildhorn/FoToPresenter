package me.kevinschildhorn.atomik.color.base

import androidx.compose.ui.graphics.Color

actual class AtomikColor actual constructor(hex: Long) {
    var platformColor: Color
        private set

    init {
        this.platformColor = Color(hex)
    }
}
