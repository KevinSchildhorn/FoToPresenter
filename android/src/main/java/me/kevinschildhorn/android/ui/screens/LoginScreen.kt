package me.kevinschildhorn.android.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import me.kevinschildhorn.common.architecture.ui.viewmodel.LoginViewModel

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun LoginScreen(viewModel: LoginViewModel) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column {
        Column {
            uiState.errorMessage?.let {
                Text(it)
            }
            TextField(uiState.hostname, onValueChange = {
                viewModel.updateHostname(it)
            }, label = {
                Text("hostname")
            })
            TextField(uiState.port, onValueChange = {
                viewModel.updatePort(it)
            }, label = {
                Text("port")
            })
            TextField(uiState.username, onValueChange = {
                viewModel.updateUsername(it)
            }, label = {
                Text("username")
            })
            TextField(uiState.password, onValueChange = {
                viewModel.updatePassword(it)
            }, label = {
                Text("password")
            })
            TextButton(
                {
                    viewModel.login()
                }, content = {
                Text("Submit")
            },
                enabled = uiState.isLoginButtonEnabled
            )
        }
    }
}
