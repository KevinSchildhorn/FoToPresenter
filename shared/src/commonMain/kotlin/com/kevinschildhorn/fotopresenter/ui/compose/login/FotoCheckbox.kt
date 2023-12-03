package com.kevinschildhorn.fotopresenter.ui.compose.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FotoCheckbox(
    title: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.End,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(title)
        Checkbox(
            checked = checked,
            onCheckedChange = { onCheckedChange }
        )
    }
}