package com.kevinschildhorn.fotopresenter.ui.screens.common.composables

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kevinschildhorn.fotopresenter.ui.atoms.FotoTypography
import com.kevinschildhorn.fotopresenter.ui.atoms.fotoColors

@Composable
fun DialogButtonText(text: String, modifier: Modifier = Modifier) {
    Text(
        text,
        style = FotoTypography().button,
        color = fotoColors.onSurface,
        modifier = modifier
    )
}

@Composable
fun DialogTitle(text: String, modifier: Modifier = Modifier) {
    Text(
        text,
        style = FotoTypography().h4,
        color = fotoColors.onSecondary,
        modifier = modifier
    )
}

@Composable
fun DialogMessage(text: String, modifier: Modifier = Modifier) {
    Text(
        text,
        style = FotoTypography().body1,
        color = fotoColors.onSurface,
        modifier = modifier
    )
}

@Composable
fun ToastMessage(text: String, modifier: Modifier = Modifier) {
    Text(
        text,
        style = FotoTypography().body1,
        color = fotoColors.onPrimary,
        modifier = modifier
    )
}