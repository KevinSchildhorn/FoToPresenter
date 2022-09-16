package me.kevinschildhorn.android.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import me.kevinschildhorn.android.ui.logic.Icon
import me.kevinschildhorn.common.viewmodel.EmailValidationViewModel

@OptIn(ExperimentalLifecycleComposeApi::class, ExperimentalLifecycleComposeApi::class)
@Composable
fun SignUpScreen(viewModel: EmailValidationViewModel) {
    val email by viewModel.email.collectAsStateWithLifecycle()
    val textFieldState by viewModel.emailTextFieldState.collectAsStateWithLifecycle()


    Column {
        OutlinedTextField(
            value = email,
            onValueChange = { newValue ->
                viewModel.updateUsername(newValue)
            },
            placeholder = { Text(textFieldState.hint) },
            trailingIcon = { textFieldState.trailingIconState.Icon() },
            modifier = Modifier.onFocusChanged {
                viewModel.focusChanged(it.isFocused)
            }
        )
        if (textFieldState.isError) {
            Text(
                text = textFieldState.errorText ?: "",
                color = Color.Red
            )
        }
        OutlinedTextField(
            value = "",
            onValueChange = {
            },
            placeholder = { Text("Temp for Focus") }
        )
    }
}