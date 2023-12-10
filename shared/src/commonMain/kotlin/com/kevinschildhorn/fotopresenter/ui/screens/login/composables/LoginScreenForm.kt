package com.kevinschildhorn.fotopresenter.ui.screens.login.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kevinschildhorn.fotopresenter.data.State
import com.kevinschildhorn.fotopresenter.extension.required
import com.kevinschildhorn.fotopresenter.ui.atoms.Padding
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.ErrorView
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.FormColumn
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.PrimaryButton
import com.kevinschildhorn.fotopresenter.ui.screens.login.LoginScreenState

@Composable
fun LoginScreenForm(
    uiState: LoginScreenState,
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
            )
        }

        FormColumn(
            verticalPadding = Padding.STANDARD,
        ) {
            LoginTextField(
                value = uiState.hostname,
                onValueChange = onHostnameChange,
                placeholder = "HostName*",
                modifier = Modifier.fillMaxWidth(),
            )
            LoginTextField(
                value = uiState.username,
                onValueChange = onUsernameChange,
                placeholder = "Username".required(),
                modifier = Modifier.fillMaxWidth(),
            )
            LoginPasswordTextField(
                value = uiState.password,
                onValueChange = onPasswordChange,
                placeholder = "Password".required(),
                modifier = Modifier.fillMaxWidth(),
            )
            LoginTextField(
                value = uiState.sharedFolder,
                onValueChange = onSharedFolderChange,
                placeholder = "Shared Folder".required(),
                modifier = Modifier.fillMaxWidth(),
            )
        }
        FormColumn(
            verticalPadding = Padding.NONE,
        ) {
            LoginCheckbox(
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
