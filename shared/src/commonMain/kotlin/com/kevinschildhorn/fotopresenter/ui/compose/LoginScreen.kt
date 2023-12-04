package com.kevinschildhorn.fotopresenter.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import com.kevinschildhorn.fotopresenter.ui.atoms.Padding
import com.kevinschildhorn.fotopresenter.ui.compose.common.TitleView
import com.kevinschildhorn.fotopresenter.ui.compose.login.LoginScreenForm
import com.kevinschildhorn.fotopresenter.ui.state.State
import com.kevinschildhorn.fotopresenter.ui.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onLoginSuccess: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()
    val uriHandler = LocalUriHandler.current

    if (uiState.state == State.SUCCESS) {
        onLoginSuccess()
    }
    Column {
        TitleView(
            "Foto",
            modifier =
            Modifier.padding(
                top = Padding.SMALL.dp,
                start = Padding.STANDARD.dp,
                bottom = Padding.LARGE.dp,
            ),
        )
        LoginScreenForm(
            uiState = uiState,
            onHostnameChange = { viewModel.updateHost(it) },
            onUsernameChange = { viewModel.updateUsername(it) },
            onPasswordChange = { viewModel.updatePassword(it) },
            onSharedFolderChange = { viewModel.updateSharedFolder(it) },
            onShouldAutoConnectChange = { viewModel.updateShouldAutoConnect(it) },
            loginButtonClicked = { viewModel.login() },
        )
        ClickableText(
            buildAnnotatedString {
                append("Setting up a QNAP NAS")
            },
            style = TextStyle(textAlign = TextAlign.Center),
            modifier = Modifier.padding(top = Padding.STANDARD.dp).fillMaxWidth(),
        ) {
            uriHandler.openUri("https://www.qnap.com/en/how-to/faq/article/how-to-map-network-drive-in-windows-os-by-qfinder")
        }
    }
}
