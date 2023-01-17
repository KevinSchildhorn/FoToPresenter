package me.kevinschildhorn.android.extensions

import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import me.kevinschildhorn.atomik.color.base.AtomikEnabledColor

// Needs to be in Android code because of bug in Compose
@Composable
fun AtomikEnabledColor.buttonColors(): ButtonColors =
    ButtonDefaults.buttonColors(
        backgroundColor = this.color.platformColor,
        disabledBackgroundColor = this.disabledColor.platformColor
    )