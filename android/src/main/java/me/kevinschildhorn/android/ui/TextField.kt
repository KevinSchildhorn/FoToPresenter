package me.kevinschildhorn.android.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import me.kevinschildhorn.android.ui.extensions.Icon
import me.kevinschildhorn.android.ui.extensions.androidColor
import me.kevinschildhorn.common.deprecated.ui.TextFieldViewModel
import me.kevinschildhorn.common.deprecated.uilogic.enums.TextFieldValidationInterface
import me.kevinschildhorn.common.design.theme.DesignMolecules

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun <T : TextFieldValidationInterface> SampleTextField(
    viewModel: TextFieldViewModel<T>,
    textCallback: (String) -> Unit,
    focusChanged: (Boolean) -> Unit
) {
    val text by viewModel.text.collectAsStateWithLifecycle()
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    val molecule = DesignMolecules.TextField.defaultTextField

    Column {
        if (state.isError) {
            Text(
                text = state.errorText ?: "",
                color = molecule.errorAtom.textColor.androidColor,
                fontSize = molecule.errorAtom.textSize.sp
            )
        }
        OutlinedTextField(
            value = text,
            onValueChange = { newValue ->
                textCallback(newValue)
            },
            textStyle = TextStyle(
                color = molecule.textEntryAtom.textColor.androidColor,
                fontSize = molecule.textEntryAtom.textSize.sp
            ),
            /*
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = state.currentBorderColor.androidColor,
                unfocusedBorderColor = state.currentBorderColor.androidColor
            ),
             */
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = state.currentSharedBorderColor.androidColor,
                unfocusedBorderColor = state.currentSharedBorderColor.androidColor,
                textColor = molecule.textEntryAtom.textColor.androidColor
            ),
            placeholder = { Text(state.hint) },
            trailingIcon = {
                state.trailingIconState.Icon {
                    textCallback("")
                }
            },
            modifier = Modifier.onFocusChanged {
                focusChanged(it.isFocused)
            }
        )
    }
}
