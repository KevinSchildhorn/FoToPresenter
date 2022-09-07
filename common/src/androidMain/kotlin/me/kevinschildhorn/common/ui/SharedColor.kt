package me.kevinschildhorn.common.ui

import androidx.compose.ui.graphics.Color


actual class SharedColor actual constructor(hex:Long)  {
    var androidColor:Color
        private set

    init {
        this.androidColor = Color(hex)
    }
}