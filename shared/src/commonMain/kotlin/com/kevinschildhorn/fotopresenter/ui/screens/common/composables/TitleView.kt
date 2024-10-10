package com.kevinschildhorn.fotopresenter.ui.screens.common.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TitleView(
    value: String,
    modifier: Modifier = Modifier,
) {
    DialogButtonText(value, modifier)
}
