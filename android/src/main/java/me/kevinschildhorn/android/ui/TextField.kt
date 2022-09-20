package me.kevinschildhorn.android.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import me.kevinschildhorn.android.ui.logic.Icon
import me.kevinschildhorn.common.uilogic.TextFieldState


@Composable
fun SampleTextField(
    email:String,
    state:TextFieldState,
    emailCallback:(String) -> Unit,
    focusChanged:(Boolean) -> Unit
) {
    Column {
        OutlinedTextField(
            value = email,
            onValueChange = { newValue ->
                emailCallback(newValue)
            },
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
        if (state.isError) {
            Text(
                text = state.errorText ?: "",
                color = Color.Red
            )
        }
    }
}