@file:Suppress("ktlint:standard:class-naming")

package com.kevinschildhorn.fotopresenter.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.kevinschildhorn.fotopresenter.ui.composables.FotoCheckbox
import com.kevinschildhorn.fotopresenter.ui.composables.FotoRadioButton
import com.kevinschildhorn.fotopresenter.ui.screens.common.ActionSheetAction
import com.kevinschildhorn.fotopresenter.ui.screens.common.ActionSheetContext
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.ActionSheet
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.ButtonState
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.ConfirmationDialog
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.PrimaryTextButton
import com.kevinschildhorn.fotopresenter.ui.screens.directory.composables.overlay.SortDialog
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
        values =
            listOf(
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
        title = "",
        initialValue = "",
        {
        },
        {
        },
    )
}

@Preview
@Composable
fun FilterDialogPreview() {
    SortDialog(
        "Hello",
        {
        },
        {
        },
    )
}

@Preview
@Composable
fun InputPreview() {
    Column {
        FotoCheckbox("Test", true, {}, horizontalArrangement = Arrangement.End)
        FotoCheckbox("Test", true, {}, horizontalArrangement = Arrangement.Start)
        FotoRadioButton("Test", true, {}, horizontalArrangement = Arrangement.End)
        FotoRadioButton("Test", true, {}, horizontalArrangement = Arrangement.Start)
    }
}
