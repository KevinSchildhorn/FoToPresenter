package com.kevinschildhorn.fotopresenter.ui.screens.common.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kevinschildhorn.fotopresenter.ui.SortingType
import com.kevinschildhorn.fotopresenter.ui.TestTags
import com.kevinschildhorn.fotopresenter.ui.atoms.fotoColors
import com.kevinschildhorn.fotopresenter.ui.testTag

@Composable
fun SortDialog(
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
                modifier = Modifier.testTag(TestTags.Directory.Sort.SORT_A_TO_Z),
            )
            Text(
                text = "File Name A-Z",
                style = MaterialTheme.typography.button,
                color = fotoColors.onSurface,
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = selectedOption.value == SortingType.NAME_DESC,
                onClick = { selectedOption.value = SortingType.NAME_DESC },
                modifier = Modifier.testTag(TestTags.Directory.Sort.SORT_Z_TO_A),
            )
            Text(
                text = "File Name Z-A",
                style = MaterialTheme.typography.button,
                color = fotoColors.onSurface,
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = selectedOption.value == SortingType.TIME_ASC,
                onClick = { selectedOption.value = SortingType.TIME_ASC },
                modifier = Modifier.testTag(TestTags.Directory.Sort.SORT_TIME_CREATED_ASC),
            )
            Text(
                text = "Time Created Ascending",
                style = MaterialTheme.typography.button,
                color = fotoColors.onSurface,
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = selectedOption.value == SortingType.TIME_DESC,
                onClick = { selectedOption.value = SortingType.TIME_DESC },
                modifier = Modifier.testTag(TestTags.Directory.Sort.SORT_TIME_CREATED_DES),
            )
            Text(
                text = "Time Created Descending",
                style = MaterialTheme.typography.button,
                color = fotoColors.onSurface,
            )
        }
    }
}

@Composable
private fun SortDialogPreview() {
    SortDialog(
        dialogTitle = "Sort By",
        onDismissRequest = {},
        onConfirmation = {},
    )
}
