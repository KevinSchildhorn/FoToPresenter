package me.kevinschildhorn.android.ui.atoms

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import me.kevinschildhorn.common.design.theme.atomic.DesignAtoms

@Composable
fun ScreenTitleTextField(
    text: String
) {
    val style = DesignAtoms.TextView.screenTitle
    Text(
        text = text,
        style = MaterialTheme.typography.h3,
    )
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
