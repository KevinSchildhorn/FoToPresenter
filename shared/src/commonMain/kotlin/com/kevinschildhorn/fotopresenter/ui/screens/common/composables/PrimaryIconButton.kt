package com.kevinschildhorn.fotopresenter.ui.screens.common.composables

import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector


@Composable
fun PrimaryIconButton(
    imageVector: ImageVector,
    modifier: Modifier = Modifier,
    buttonState: ButtonState = ButtonState.ENABLED,
    onClick: () -> Unit,
) {
    PrimaryButton(modifier = modifier, buttonState = buttonState, onClick = onClick) {
        Icon(imageVector, contentDescription = null)
    }
}