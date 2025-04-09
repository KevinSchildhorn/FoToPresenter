package com.kevinschildhorn.fotopresenter.ui.screens.login.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun LoginCheckbox(
    title: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.End,
    modifier: Modifier = Modifier,
    checkBoxModifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = horizontalArrangement,
        modifier = modifier.fillMaxWidth(),
    ) {
        Text(title)
        Checkbox(
            checked = checked,
            modifier = checkBoxModifier,
            onCheckedChange = onCheckedChange,
        )
    }
}
