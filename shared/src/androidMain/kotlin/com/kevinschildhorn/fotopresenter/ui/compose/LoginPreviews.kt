package com.kevinschildhorn.fotopresenter.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.kevinschildhorn.fotopresenter.ui.UiState
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.ErrorView
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.TitleView
import com.kevinschildhorn.fotopresenter.ui.screens.login.LoginScreenState
import com.kevinschildhorn.fotopresenter.ui.screens.login.composables.LoginPasswordTextField
import com.kevinschildhorn.fotopresenter.ui.screens.login.composables.LoginScreenForm
import com.kevinschildhorn.fotopresenter.ui.screens.login.composables.LoginTextField

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
