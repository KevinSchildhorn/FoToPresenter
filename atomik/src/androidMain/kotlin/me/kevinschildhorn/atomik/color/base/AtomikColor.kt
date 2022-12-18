package me.kevinschildhorn.atomik.color.base

import androidx.compose.ui.graphics.Color

actual class AtomikColor actual constructor(hex: Long) {
    var androidColor: Color
        private set

    init {
        this.androidColor = Color(hex)
    }
}