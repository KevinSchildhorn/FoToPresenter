package me.kevinschildhorn.android.ui

import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import me.kevinschildhorn.android.ui.logic.Icon
import me.kevinschildhorn.common.viewmodel.EmailValidationViewModel

@OptIn(ExperimentalLifecycleComposeApi::class, ExperimentalLifecycleComposeApi::class)
@Composable
fun SignUpScreen(viewModel: EmailValidationViewModel) {
    val userNameHasError by viewModel.userNameHasError.collectAsStateWithLifecycle()
    val textFieldState by viewModel.textFieldState.collectAsStateWithLifecycle()

    OutlinedTextField(
        value = viewModel.username,
        onValueChange = { newValue ->
            viewModel.updateUsername(newValue)
        },
        placeholder = { Text(textFieldState.hint) },
        trailingIcon = { textFieldState.trailingIconState.Icon() }
    
    )

    OutlinedTextField(
        value = "",
        onValueChange = {
        },
        placeholder = { Text("Temp for Focus") }
    )

/*
    if (textFieldState.isError) {
        Text(
            text = textFieldState.errorText ?: "",
            color = Color.Red
        )
    }*/
}