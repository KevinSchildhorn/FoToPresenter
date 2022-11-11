package me.kevinschildhorn.android.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import me.kevinschildhorn.common.layers.ui.viewmodel.LoginViewModel

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun SignUpScreen(viewModel: LoginViewModel) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column {
        Column {
            uiState.errorMessage?.let {
                Text(it)
            }
            TextField(uiState.address, onValueChange = {
                viewModel.updateAddress(it)
            }, label = {
                Text("address")
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
            TextButton({
                viewModel.login()
            }, content = {
                Text("Submit")
            })
        }
    }
}