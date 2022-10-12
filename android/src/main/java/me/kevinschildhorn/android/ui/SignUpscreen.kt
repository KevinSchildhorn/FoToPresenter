package me.kevinschildhorn.android.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import me.kevinschildhorn.common.viewmodel.EmailValidationViewModel

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun SignUpScreen(viewModel: EmailValidationViewModel) {

    val buttonState by viewModel.createProfileButtonState.uiState.collectAsStateWithLifecycle()

    Column {
        SampleTextField(viewModel.emailTextState, {
            viewModel.updateEmail(it)
        }, { isFocused ->
            viewModel.emailTextState.setFocus(isFocused)
        })
        SampleTextField(viewModel.passwordTextState, {
            viewModel.updatePassword(it)
        }, { isFocused ->
            viewModel.passwordTextState.setFocus(isFocused)
        })
        Button(onClick = {
            viewModel.createProfile()
        }, content = {
            if(buttonState.isLoading) {
                CircularProgressIndicator(
                    color = buttonState.textColor.androidColor
                )
            }else {
                Text(
                    buttonState.text,
                    color = buttonState.textColor.androidColor
                )
            }
        },
        enabled = buttonState.isEnabled
        )
    }
}