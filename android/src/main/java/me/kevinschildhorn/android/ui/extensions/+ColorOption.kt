package me.kevinschildhorn.android.ui.extensions

import androidx.compose.ui.graphics.Color
import me.kevinschildhorn.common.deprecated.uilogic.enums.ColorOption
import me.kevinschildhorn.common.deprecated.uilogic.enums.SharedColorOption

val ColorOption.androidColor: Color
    get() = when (this) {
        ColorOption.NORMAL -> Color.Blue
        ColorOption.ERROR -> Color.Red
        ColorOption.HINT -> Color.Gray
        ColorOption.SUCCESS -> Color.Green
    }

val SharedColorOption.androidColor: Color
    get() = this.sharedColor.androidColor
