package com.kevinschildhorn.fotopresenter.ui.composables.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.kevinschildhorn.fotopresenter.ui.LoginViewModel
import com.kevinschildhorn.fotopresenter.ui.theme.FotoPresenterTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel
) {
    val gameUiState by loginViewModel.uiState.collectAsState()

    Column {
        OutlinedTextField(
            value = gameUiState.hostName,
            label = { Text("Host") },
            onValueChange = {
                loginViewModel.updateHost(it)
            }
        )
        OutlinedTextField(
            value = gameUiState.username,
            label = { Text("Name") },
            onValueChange = {
                loginViewModel.updateUsername(it)
            }
        )
        OutlinedTextField(
            value = gameUiState.password,
            label = { Text("Password") },
            onValueChange = {
                loginViewModel.updatePassword(it)
            },
        )
        Button(onClick = { loginViewModel.login() }) {
            Text(text = "Login")
        }
    }
}