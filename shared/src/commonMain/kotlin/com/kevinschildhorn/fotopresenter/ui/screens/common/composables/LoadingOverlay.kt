package com.kevinschildhorn.fotopresenter.ui.screens.common.composables

import androidx.compose.foundation.layout.width
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kevinschildhorn.fotopresenter.ui.atoms.fotoColors

@Composable
fun LoadingOverlay() {
    Overlay(
        z = 8f,
        visible = true,
        onDismiss = {},
    ) {
        CircularProgressIndicator(
            modifier = Modifier.width(75.dp).align(Alignment.Center),
            color = fotoColors.primary,
        )
    }
}
