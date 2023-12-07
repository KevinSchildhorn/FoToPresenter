package com.kevinschildhorn.fotopresenter.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.kevinschildhorn.fotopresenter.ui.compose.common.ErrorView
import com.kevinschildhorn.fotopresenter.ui.compose.common.TitleView
import com.kevinschildhorn.fotopresenter.ui.compose.login.LoginPasswordTextField
import com.kevinschildhorn.fotopresenter.ui.compose.login.LoginTextField
import com.kevinschildhorn.fotopresenter.ui.compose.login.LoginScreenForm
import com.kevinschildhorn.fotopresenter.ui.state.LoginScreenState
import com.kevinschildhorn.fotopresenter.ui.state.UiState

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
        LoginTextField("", {}, "Hint")
        LoginTextField("Value", {}, "Hint")
        LoginPasswordTextField("", {}, "Password")
        LoginPasswordTextField("Value", {}, "Password")
    }
}

@Preview
@Composable
fun LoginScreenFormPreview() {
    LoginScreenForm(
        LoginScreenState(
            state = UiState.ERROR("No Internet"),
        ),
        {},
        {},
        {},
        {},
        {},
        {},
    )
}
