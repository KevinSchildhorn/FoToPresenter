package me.kevinschildhorn.common.design.atomicLib.theme

import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import me.kevinschildhorn.common.design.atomicLib.color.SharedEnabledColor

@Composable
fun SharedEnabledColor.asComposable(): ButtonColors =
    ButtonDefaults.buttonColors(
        backgroundColor = this.color.androidColor,
        disabledBackgroundColor = this.disabledColor.androidColor
    )
