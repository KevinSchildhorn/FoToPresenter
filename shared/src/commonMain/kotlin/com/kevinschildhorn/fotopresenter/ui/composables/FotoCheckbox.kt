package com.kevinschildhorn.fotopresenter.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.kevinschildhorn.fotopresenter.ui.atoms.fotoColors

@Composable
fun FotoCheckbox(
    title: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.End,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    checkBoxModifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = horizontalArrangement,
        modifier = modifier.fillMaxWidth(),
    ) {
        if(horizontalArrangement == Arrangement.End) FotoCheckboxText(title)
        Checkbox(
            checked = checked,
            modifier = checkBoxModifier,
            onCheckedChange = onCheckedChange,
            enabled = enabled,
        )
        if(horizontalArrangement != Arrangement.End) FotoCheckboxText(title)
    }
}

@Composable
private fun FotoCheckboxText(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.button,
        color = fotoColors.onSurface,
    )
}
