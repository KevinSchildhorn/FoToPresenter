package com.kevinschildhorn.fotopresenter.ui.screens.common.composables

import androidx.compose.runtime.Composable

@Composable
fun ConfirmationDialog(
    dialogTitle: String,
    dialogText: String,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
) {
    FotoDialog(
        dialogTitle = dialogTitle,
        onDismissRequest = onDismissRequest,
        onConfirmation = onConfirmation,
    ) {
        DialogButtonText(dialogText)
    }
}
