package com.kevinschildhorn.fotopresenter.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.kevinschildhorn.fotopresenter.ui.screens.common.ActionSheetAction
import com.kevinschildhorn.fotopresenter.ui.screens.common.ActionSheetContext
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.ActionSheet
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.ButtonState
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.ConfirmationDialog
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.FilterDialog
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.PrimaryTextButton
import com.kevinschildhorn.fotopresenter.ui.screens.playlist.composables.TextEntryDialog

@Preview
@Composable
fun PrimaryButtonPreview() {
    Column {
        PrimaryTextButton("Enabled", buttonState = ButtonState.ENABLED) {}
        PrimaryTextButton("Disabled", buttonState = ButtonState.DISABLED) {}
        PrimaryTextButton("Loading", buttonState = ButtonState.LOADING) {}
    }
}

@Preview
@Composable
fun ActionSheetPreview() {
    ActionSheet(
        visible = true,
        offset = 200,
        values = listOf(
            ActionSheetContext(ActionSheetAction.START_SLIDESHOW, 1),
            ActionSheetContext(ActionSheetAction.NONE, 2),
        ),
        onClick = {},
        onDismiss = {},
    )
}

@Preview
@Composable
fun ConfirmationDialogPreview() {
    ConfirmationDialog(
        "Hello",
        "World",
        {

        },
        {

        },
    )
}

@Preview
@Composable
fun TextConfirmationDialogPreview() {
    TextEntryDialog(
        {

        },
        {

        },
    )
}

@Preview
@Composable
fun FilterDialogPreview() {
    FilterDialog(
        "Hello",
        {

        },
        {

        },
    )
}

