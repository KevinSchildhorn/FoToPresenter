package com.kevinschildhorn.fotopresenter.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.kevinschildhorn.fotopresenter.ui.compose.common.ErrorView
import com.kevinschildhorn.fotopresenter.ui.compose.common.TitleView
import com.kevinschildhorn.fotopresenter.ui.compose.login.FotoPasswordTextField
import com.kevinschildhorn.fotopresenter.ui.compose.login.FotoTextField
import com.kevinschildhorn.fotopresenter.ui.compose.login.LoginScreenForm
import com.kevinschildhorn.fotopresenter.ui.state.LoginScreenState
import com.kevinschildhorn.fotopresenter.ui.state.State
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
        FotoTextField("", {}, "Hint")
        FotoTextField("Value", {}, "Hint")
        FotoPasswordTextField("", {}, "Password")
        FotoPasswordTextField("Value", {}, "Password")
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
