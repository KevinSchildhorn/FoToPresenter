package me.kevinschildhorn.android.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import me.kevinschildhorn.common.viewmodel.EmailValidationViewModel

@OptIn(ExperimentalLifecycleComposeApi::class, ExperimentalLifecycleComposeApi::class)
@Composable
fun SignUpScreen(viewModel: EmailValidationViewModel) {
    val email by viewModel.email.collectAsStateWithLifecycle()
    val emailTextFieldState by viewModel.emailTextFieldState.collectAsStateWithLifecycle()

    val password by viewModel.password.collectAsStateWithLifecycle()
    val passwordTextFieldState by viewModel.passwordTextFieldState.collectAsStateWithLifecycle()

    Column {
        SampleTextField(email, emailTextFieldState, {
            viewModel.updateEmail(it)
        }, { isFocused ->
            viewModel.emailFocusChanged(isFocused)
        })
        SampleTextField(password, passwordTextFieldState, {
            viewModel.updatePassword(it)
        }, { isFocused ->
            viewModel.passwordFocusChanged(isFocused)
        })
        OutlinedTextField(
            value = "",
            onValueChange = {
            },
            placeholder = { Text("Temp for Focus") }
        )
    }
}