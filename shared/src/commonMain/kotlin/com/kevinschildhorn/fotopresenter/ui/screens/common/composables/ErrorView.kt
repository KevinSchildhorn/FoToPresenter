package com.kevinschildhorn.fotopresenter.ui.screens.common.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ErrorView(
    message: String,
    modifier: Modifier = Modifier,
) {
    var configuredModifier: Modifier = modifier
    Box(
        modifier =
            configuredModifier.padding(
                horizontal = 16.dp,
                vertical = 16.dp,
            ).fillMaxWidth(),
    ) {
        DialogButtonText(message)
    }
}
