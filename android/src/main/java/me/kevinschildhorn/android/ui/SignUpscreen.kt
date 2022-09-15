package me.kevinschildhorn.android.ui

import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import me.kevinschildhorn.common.viewmodel.EmailValidationViewModel

@OptIn(ExperimentalLifecycleComposeApi::class, ExperimentalLifecycleComposeApi::class)
@Composable
fun SignUpScreen(viewModel: EmailValidationViewModel) {

    OutlinedTextField(
        value = viewModel.username,
        onValueChange = { newValue ->
            viewModel.updateUsername(newValue)
        }
    )

    val userNameHasError by viewModel.userNameHasError.collectAsStateWithLifecycle()

    if (userNameHasError) {
        Text(
            text = "Username not available. Please choose a different one.",
            color = Color.Red
        )
    }
}