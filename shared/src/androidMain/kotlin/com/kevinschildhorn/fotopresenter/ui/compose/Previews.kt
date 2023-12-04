package com.kevinschildhorn.fotopresenter.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.kevinschildhorn.fotopresenter.ui.compose.common.ButtonState
import com.kevinschildhorn.fotopresenter.ui.compose.common.ErrorView
import com.kevinschildhorn.fotopresenter.ui.compose.common.PrimaryButton
import com.kevinschildhorn.fotopresenter.ui.compose.common.TitleView
import com.kevinschildhorn.fotopresenter.ui.compose.login.FotoPasswordTextField
import com.kevinschildhorn.fotopresenter.ui.compose.login.FotoTextField
import com.kevinschildhorn.fotopresenter.ui.compose.login.LoginScreenForm
import com.kevinschildhorn.fotopresenter.ui.state.LoginUiState
import com.kevinschildhorn.fotopresenter.ui.state.State

@Preview
@Composable
fun TitleViewPreview() {
    TitleView("Foto")
}

@Preview
@Composable
fun ErrorViewPreview() {
    ErrorView("Error!")
}

@Preview
@Composable
fun FotoTextFieldPreview() {
    Column {
        FotoTextField("", {}, "Hint")
        FotoTextField("Value", {}, "Hint")
        FotoPasswordTextField("", {}, "Password")
        FotoPasswordTextField("Value", {}, "Password")
    }
}

@Preview
@Composable
fun PrimaryButtonPreview() {
    Column {
        PrimaryButton("Enabled", buttonState = ButtonState.ENABLED) {}
        PrimaryButton("Disabled", buttonState = ButtonState.DISABLED) {}
        PrimaryButton("Loading", buttonState = ButtonState.LOADING) {}
    }
}

@Preview
@Composable
fun LoginScreenFormPreview() {
    LoginScreenForm(
        LoginUiState(
            state = State.ERROR("No Internet"),
        ),
        {},
        {},
        {},
        {},
        {},
        {},
    )
}
