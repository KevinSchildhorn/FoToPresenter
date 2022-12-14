package me.kevinschildhorn.android.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun SampleTextField(
    textCallback: (String) -> Unit,
    focusChanged: (Boolean) -> Unit
) {
    /*
    //val text by viewModel.text.collectAsStateWithLifecycle()
    //val state by viewModel.uiState.collectAsStateWithLifecycle()

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
    }*/
}
