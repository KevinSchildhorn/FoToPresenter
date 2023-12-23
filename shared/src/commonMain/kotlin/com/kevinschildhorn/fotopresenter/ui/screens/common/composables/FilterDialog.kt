package com.kevinschildhorn.fotopresenter.ui.screens.common.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.material.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import com.kevinschildhorn.fotopresenter.ui.SortingType
import com.kevinschildhorn.fotopresenter.ui.screens.common.CommonAtoms.dialogButton

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
                onClick = { selectedOption.value = SortingType.NAME_ASC }
            )
            AtomikText("File Name A-Z",dialogButton)
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = selectedOption.value == SortingType.NAME_DESC,
                onClick = { selectedOption.value = SortingType.NAME_DESC }
            )
            AtomikText("File Name Z-A",dialogButton)
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = selectedOption.value == SortingType.TIME_ASC,
                onClick = { selectedOption.value = SortingType.TIME_ASC }
            )
            AtomikText("Time Created Ascending",dialogButton)
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = selectedOption.value == SortingType.TIME_DESC,
                onClick = { selectedOption.value = SortingType.TIME_DESC }
            )
            AtomikText("Time Created Descending",dialogButton)
        }
    }
}