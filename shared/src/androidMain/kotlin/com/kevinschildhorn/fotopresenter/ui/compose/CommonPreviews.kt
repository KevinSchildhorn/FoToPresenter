package com.kevinschildhorn.fotopresenter.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.kevinschildhorn.fotopresenter.ui.compose.common.ActionSheet
import com.kevinschildhorn.fotopresenter.ui.compose.common.ButtonState
import com.kevinschildhorn.fotopresenter.ui.compose.common.PrimaryButton

@Preview
@Composable
fun PrimaryButtonPreview() {
    Column {
        PrimaryButton("Enabled", buttonState = ButtonState.ENABLED) {}
        PrimaryButton("Disabled", buttonState = ButtonState.DISABLED) {}
        PrimaryButton("Loading", buttonState = ButtonState.LOADING) {}
    }
}

@Preview
@Composable
fun ActionSheetPreview() {
    ActionSheet(
        true,
        offset = 200,
        values = listOf("Option A", "Option B")
    ){

    }
}