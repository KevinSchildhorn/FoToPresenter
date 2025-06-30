package com.kevinschildhorn.fotopresenter.ui.screens.directory.composables.overlay

import androidx.compose.runtime.Composable
import com.kevinschildhorn.fotopresenter.ui.screens.common.ActionSheetAction
import com.kevinschildhorn.fotopresenter.ui.screens.common.ActionSheetContext
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.ActionSheet

@Composable
fun DirectoryOptionsOverlay(
    onAction: (ActionSheetAction) -> Unit,
    onDismiss: () -> Unit,
) {
    ActionSheet(
        visible = true,
        offset = 200,
        values =
            listOf(
                ActionSheetContext(ActionSheetAction.START_SLIDESHOW, 0),
                // ActionSheetContext(ActionSheetAction.ADD_ALL_LOCATION, 1),
            ),
        onClick = { onAction(it.action) },
        onDismiss = onDismiss,
    )
}
