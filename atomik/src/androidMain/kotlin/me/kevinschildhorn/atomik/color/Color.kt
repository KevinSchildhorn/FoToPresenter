package me.kevinschildhorn.atomik.color

import androidx.compose.ui.graphics.Color

actual class Color actual constructor(hex: Long) {
    var androidColor: Color
        private set

    init {
        this.androidColor = Color(hex)
    }
}
