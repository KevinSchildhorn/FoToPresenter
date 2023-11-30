package com.kevinschildhorn.fotopresenter.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.buildAnnotatedString
import com.kevinschildhorn.fotopresenter.ui.viewmodel.LoginViewModel

@Composable
fun LoginScreen(viewModel: LoginViewModel) {

    val uiState by viewModel.uiState.collectAsState()
    val uriHandler = LocalUriHandler.current

    Column {
        LoginTextField(
            value = uiState.hostname,
            onValueChange = { viewModel.updateHost(it) },
            placeholder = "HostName"
        )
        LoginTextField(
            value = uiState.username,
            onValueChange = { viewModel.updateUsername(it) },
            placeholder = "HostName"
        )
        LoginTextField(
            value = uiState.password,
            onValueChange = { viewModel.updatePassword(it) },
            placeholder = "HostName"
        )
        LoginTextField(
            value = uiState.sharedFolder,
            onValueChange = { viewModel.updateSharedFolder(it) },
            placeholder = "HostName"
        )
        Checkbox(
            checked = uiState.shouldAutoConnect,
            onCheckedChange = { viewModel.updateShouldAutoConnect(it) }
        )
        Button({}) {
            Text("Login")
        }

        ClickableText(
            buildAnnotatedString {
                append("Setting up a QNAP NAS")

            }
        ) {
            uriHandler.openUri("https://www.qnap.com/en/how-to/faq/article/how-to-map-network-drive-in-windows-os-by-qfinder")
        }
    }
}

@Composable
fun LoginTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(placeholder)
        }
    )
}