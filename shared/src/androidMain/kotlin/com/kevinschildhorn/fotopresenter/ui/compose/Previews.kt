package com.kevinschildhorn.fotopresenter.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun FotoTextFieldPreview() {
    Column {
        FotoTextField("", {}, "Hint")
        FotoTextField("Value", {}, "Hint")
        FotoPasswordTextField("", {}, "Hint")
        FotoPasswordTextField("Value", {}, "Hint")
    }
}