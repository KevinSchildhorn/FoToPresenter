package com.kevinschildhorn.fotopresenter.ui.screens.login.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kevinschildhorn.fotopresenter.extension.required
import com.kevinschildhorn.fotopresenter.ui.TestTags
import com.kevinschildhorn.fotopresenter.ui.UiState
import com.kevinschildhorn.fotopresenter.ui.atoms.Padding
import com.kevinschildhorn.fotopresenter.ui.composables.FotoCheckbox
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.ErrorView
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.FormColumn
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.PrimaryTextButton
import com.kevinschildhorn.fotopresenter.ui.screens.login.LoginScreenState
import com.kevinschildhorn.fotopresenter.ui.testTag

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
        (uiState.state as? UiState.ERROR)?.let {
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
                modifier = Modifier.fillMaxWidth().testTag(TestTags.Login.HOST_NAME),
            )
            LoginTextField(
                value = uiState.username,
                onValueChange = onUsernameChange,
                placeholder = "Username".required(),
                modifier = Modifier.fillMaxWidth().testTag(TestTags.Login.USERNAME),
            )
            LoginPasswordTextField(
                value = uiState.password,
                onValueChange = onPasswordChange,
                placeholder = "Password".required(),
                modifier = Modifier.fillMaxWidth().testTag(TestTags.Login.PASSWORD),
            )
            LoginTextField(
                value = uiState.sharedFolder,
                onValueChange = onSharedFolderChange,
                placeholder = "Shared Folder".required(),
                modifier = Modifier.fillMaxWidth().testTag(TestTags.Login.SHARED_FOLDER),
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
                checkBoxModifier = Modifier.testTag(TestTags.Login.AUTO_CONNECT),
            )
            PrimaryTextButton(
                title = "Log In",
                onClick = loginButtonClicked,
                modifier = Modifier.fillMaxWidth().testTag(TestTags.Login.LOGIN_BUTTON),
                buttonState = uiState.loginButtonState,
            )
        }
    }
}
