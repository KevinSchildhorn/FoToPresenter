package com.kevinschildhorn.fotopresenter.ui.compose.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kevinschildhorn.fotopresenter.extension.required
import com.kevinschildhorn.fotopresenter.ui.atoms.Padding
import com.kevinschildhorn.fotopresenter.ui.compose.common.ErrorView
import com.kevinschildhorn.fotopresenter.ui.compose.common.FormColumn
import com.kevinschildhorn.fotopresenter.ui.compose.common.PrimaryButton
import com.kevinschildhorn.fotopresenter.ui.state.LoginUiState
import com.kevinschildhorn.fotopresenter.ui.state.State

@Composable
fun LoginScreenForm(
    uiState: LoginUiState,
    onHostnameChange: (String) -> Unit,
    onUsernameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSharedFolderChange: (String) -> Unit,
    onShouldAutoConnectChange: (Boolean) -> Unit,
    loginButtonClicked: () -> Unit,
) {
    Column(modifier = Modifier.padding(horizontal = 20.dp)) {
        (uiState.state as? State.ERROR)?.let {
            ErrorView(
                "Error Occurred! ${it.message}",
                modifier = Modifier.fillMaxWidth(),
            )
        }

        FormColumn(
            verticalPadding = Padding.STANDARD,
        ) {
            FotoTextField(
                value = uiState.hostname,
                onValueChange = onHostnameChange,
                placeholder = "HostName*",
                modifier = Modifier.fillMaxWidth(),
            )
            FotoTextField(
                value = uiState.username,
                onValueChange = onUsernameChange,
                placeholder = "Username".required(),
                modifier = Modifier.fillMaxWidth(),
            )
            FotoPasswordTextField(
                value = uiState.password,
                onValueChange = onPasswordChange,
                placeholder = "Password".required(),
                modifier = Modifier.fillMaxWidth(),
            )
            FotoTextField(
                value = uiState.sharedFolder,
                onValueChange = onSharedFolderChange,
                placeholder = "Shared Folder".required(),
                modifier = Modifier.fillMaxWidth(),
            )
        }
        FormColumn(
            verticalPadding = Padding.NONE,
        ) {
            FotoCheckbox(
                title = "Should autoConnect",
                checked = uiState.shouldAutoConnect,
                onCheckedChange = onShouldAutoConnectChange,
                modifier = Modifier.fillMaxWidth(),
            )
            PrimaryButton(
                title = "Log In",
                onClick = loginButtonClicked,
                modifier = Modifier.fillMaxWidth(),
                buttonState = uiState.loginbuttonState,
            )
        }
    }
}
