package me.kevinschildhorn.android.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import me.kevinschildhorn.android.ui.extensions.Icon
import me.kevinschildhorn.android.ui.extensions.androidColor
import me.kevinschildhorn.common.uilogic.enums.TextFieldState


@Composable
fun SampleTextField(
    email:String,
    state: TextFieldState,
    emailCallback:(String) -> Unit,
    focusChanged:(Boolean) -> Unit
) {
    Column {
        if (state.isError) {
            Text(
                text = state.errorText ?: "",
                color = Color.Red
            )
        }
        OutlinedTextField(
            value = email,
            onValueChange = { newValue ->
                emailCallback(newValue)
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
                    emailCallback("")
                }
            },
            modifier = Modifier.onFocusChanged {
                focusChanged(it.isFocused)
            }
        )

    }
}