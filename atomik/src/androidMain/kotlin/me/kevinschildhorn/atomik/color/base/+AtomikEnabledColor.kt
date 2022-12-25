package me.kevinschildhorn.atomik.color.base

import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable

@Composable
fun AtomikEnabledColor.test123(): ButtonColors =
    ButtonDefaults.buttonColors(
        backgroundColor = this.color.platformColor,
        disabledBackgroundColor = this.disabledColor.platformColor
    )
