package com.kevinschildhorn.fotopresenter.ui.screens.common.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import com.kevinschildhorn.fotopresenter.ui.SortingType
import com.kevinschildhorn.fotopresenter.ui.atoms.FotoTypography
import com.kevinschildhorn.fotopresenter.ui.atoms.fotoColors

@Composable
fun FilterDialog(
    dialogTitle: String,
    onDismissRequest: () -> Unit,
    onConfirmation: (SortingType) -> Unit,
) {
    val selectedOption = remember { mutableStateOf(SortingType.NAME_ASC) }

    FotoDialog(
        dialogTitle,
        onDismissRequest = onDismissRequest,
        onConfirmation = {
            onConfirmation(selectedOption.value)
        },
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = selectedOption.value == SortingType.NAME_ASC,
                onClick = { selectedOption.value = SortingType.NAME_ASC },
            )
            Text(
                text = "File Name A-Z",
                style = FotoTypography().button,
                color = fotoColors.onSurface,
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = selectedOption.value == SortingType.NAME_DESC,
                onClick = { selectedOption.value = SortingType.NAME_DESC },
            )
            Text(
                text = "File Name Z-A",
                style = FotoTypography().button,
                color = fotoColors.onSurface,
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = selectedOption.value == SortingType.TIME_ASC,
                onClick = { selectedOption.value = SortingType.TIME_ASC },
            )
            Text(
                text = "Time Created Ascending",
                style = FotoTypography().button,
                color = fotoColors.onSurface,
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = selectedOption.value == SortingType.TIME_DESC,
                onClick = { selectedOption.value = SortingType.TIME_DESC },
            )
            Text(
                text = "Time Created Descending",
                style = FotoTypography().button,
                color = fotoColors.onSurface,
            )
        }
    }
}
