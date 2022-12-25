package me.kevinschildhorn.android.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import me.kevinschildhorn.android.ui.atoms.PrimaryButton
import me.kevinschildhorn.common.architecture.ui.viewmodel.LoginViewModel

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun LoginScreen(viewModel: LoginViewModel) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column {
        PrimaryButton("Login"){

        }
    }
}
