package me.kevinschildhorn.atomik.color

import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import me.kevinschildhorn.atomik.color.base.AtomikEnabledColor

@Composable
fun AtomikEnabledColor.asComposable(): ButtonColors =
    ButtonDefaults.buttonColors(
        backgroundColor = this.color.androidColor,
        disabledBackgroundColor = this.disabledColor.androidColor
    )
