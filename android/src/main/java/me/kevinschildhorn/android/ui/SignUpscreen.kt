package me.kevinschildhorn.android.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
            TextField(uiState.content.address, onValueChange = {
                viewModel.address = it
            }, label = {
                Text("address")
            })
            TextField(uiState.content.username, onValueChange = {
                viewModel.username = it
            }, label = {
                Text("username")
            })
            TextField(uiState.content.password, onValueChange = {
                viewModel.password = it
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