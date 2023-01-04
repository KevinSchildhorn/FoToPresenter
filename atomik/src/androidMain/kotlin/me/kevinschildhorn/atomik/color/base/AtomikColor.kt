package me.kevinschildhorn.atomik.color.base

import androidx.compose.ui.graphics.Color

actual class AtomikColor actual constructor(val hex: Long) {
    var platformColor: Color
        private set

    init {
        this.platformColor = Color(hex)
    }
}
