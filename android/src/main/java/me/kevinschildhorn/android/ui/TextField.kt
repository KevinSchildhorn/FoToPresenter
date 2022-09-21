package me.kevinschildhorn.android.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import me.kevinschildhorn.android.ui.extensions.Icon
import me.kevinschildhorn.android.ui.extensions.androidColor
import me.kevinschildhorn.common.uilogic.enums.TextFieldValidationInterface
import me.kevinschildhorn.common.viewmodel.ui.TextFieldViewModel


@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun <T:TextFieldValidationInterface> SampleTextField(
    viewModel: TextFieldViewModel<T>,
    textCallback:(String) -> Unit,
    focusChanged:(Boolean) -> Unit
) {
    val text by viewModel.text.collectAsStateWithLifecycle()
    val state by viewModel.uiState.collectAsStateWithLifecycle()


    Column {
        if (state.isError) {
            Text(
                text = state.errorText ?: "",
                color = Color.Red
            )
        }
        OutlinedTextField(
            value = text,
            onValueChange = { newValue ->
                textCallback(newValue)
            },
            /*
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = state.currentBorderColor.androidColor,
                unfocusedBorderColor = state.currentBorderColor.androidColor
            ),
             */
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = state.currentSharedBorderColor.androidColor,
                unfocusedBorderColor = state.currentSharedBorderColor.androidColor
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