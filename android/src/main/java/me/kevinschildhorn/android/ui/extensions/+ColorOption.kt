package me.kevinschildhorn.android.ui.extensions

import androidx.compose.ui.graphics.Color
import me.kevinschildhorn.common.ui.ColorOption

val ColorOption.androidColor: Color
    get() = when (this) {
        ColorOption.NORMAL -> Color.Blue
        ColorOption.ERROR -> Color.Red
        ColorOption.HINT -> Color.Gray
        ColorOption.SUCCESS -> Color.Green
    }