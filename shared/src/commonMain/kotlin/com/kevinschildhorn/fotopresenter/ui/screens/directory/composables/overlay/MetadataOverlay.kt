package com.kevinschildhorn.fotopresenter.ui.screens.directory.composables.overlay

import androidx.compose.runtime.Composable
import com.kevinschildhorn.fotopresenter.ui.screens.playlist.composables.TextEntryDialog

@Composable
fun MetadataOverlay(
    initialValue: String?,
    onConfirmation: (String) -> Unit,
    onDismiss: () -> Unit,
) {
    TextEntryDialog(
        title = "Add Keywords",
        initialValue = initialValue ?: "",
        onDismissRequest = onDismiss,
        onConfirmation = onConfirmation,
    )
}
