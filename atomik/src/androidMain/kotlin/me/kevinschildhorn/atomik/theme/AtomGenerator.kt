package me.kevinschildhorn.atomik.theme

import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import me.kevinschildhorn.atomik.color.SharedEnabledColor

@Composable
fun me.kevinschildhorn.atomik.color.SharedEnabledColor.asComposable(): ButtonColors =
    ButtonDefaults.buttonColors(
        backgroundColor = this.color.androidColor,
        disabledBackgroundColor = this.disabledColor.androidColor
    )
