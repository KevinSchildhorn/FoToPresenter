package com.kevinschildhorn.fotopresenter.ui.screens.common.composables

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DialogButtonText(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text,
        style = MaterialTheme.typography.button,
        color = MaterialTheme.colors.onSurface,
        modifier = modifier,
    )
}

@Composable
fun DialogTitle(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text,
        style = MaterialTheme.typography.h4,
        color = MaterialTheme.colors.onSecondary,
        modifier = modifier,
    )
}

@Composable
fun DialogMessage(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text,
        style = MaterialTheme.typography.body1,
        color = MaterialTheme.colors.onSurface,
        modifier = modifier,
    )
}

@Composable
fun ToastMessage(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text,
        style = MaterialTheme.typography.body1,
        color = MaterialTheme.colors.onPrimary,
        modifier = modifier,
    )
}
