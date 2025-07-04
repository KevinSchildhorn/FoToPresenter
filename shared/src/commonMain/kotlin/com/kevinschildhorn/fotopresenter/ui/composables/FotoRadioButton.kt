package com.kevinschildhorn.fotopresenter.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kevinschildhorn.fotopresenter.ui.atoms.disabled

@Composable
fun FotoRadioButton(
    title: String,
    selected: Boolean,
    onRadioChanged: () -> Unit,
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
        if (horizontalArrangement == Arrangement.End) FotoRadioText(title, enabled)
        RadioButton(
            selected = selected,
            onClick = onRadioChanged,
            modifier = checkBoxModifier,
            enabled = enabled,
        )
        if (horizontalArrangement != Arrangement.End) FotoRadioText(title, enabled)
    }
}

@Composable
private fun FotoRadioText(
    title: String,
    enabled: Boolean,
) {
    Text(
        text = title,
        style = MaterialTheme.typography.button,
        color = if (enabled) MaterialTheme.colors.onSurface else disabled,
    )
}
