package com.kevinschildhorn.fotopresenter.ui.screens.common.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.kevinschildhorn.fotopresenter.ui.SortingType
import com.kevinschildhorn.fotopresenter.ui.TestTags
import com.kevinschildhorn.fotopresenter.ui.composables.FotoRadioButton
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
        FotoRadioButton(
            title = "File Name A-Z",
            selected = selectedOption.value == SortingType.NAME_ASC,
            onRadioChanged = { selectedOption.value = SortingType.NAME_ASC },
            modifier = Modifier.testTag(TestTags.Directory.Sort.SORT_A_TO_Z)
        )
        FotoRadioButton(
            title = "File Name Z-A",
            selected = selectedOption.value == SortingType.NAME_DESC,
            onRadioChanged = { selectedOption.value = SortingType.NAME_DESC },
            modifier = Modifier.testTag(TestTags.Directory.Sort.SORT_Z_TO_A)
        )
        FotoRadioButton(
            title = "Time Created Ascending",
            selected = selectedOption.value == SortingType.TIME_ASC,
            onRadioChanged = { selectedOption.value = SortingType.TIME_ASC },
            modifier = Modifier.testTag(TestTags.Directory.Sort.SORT_TIME_CREATED_ASC)
        )
        FotoRadioButton(
            title = "Time Created Descending",
            selected = selectedOption.value == SortingType.TIME_DESC,
            onRadioChanged = { selectedOption.value = SortingType.TIME_DESC },
            modifier = Modifier.testTag(TestTags.Directory.Sort.SORT_TIME_CREATED_DES)
        )
    }
}