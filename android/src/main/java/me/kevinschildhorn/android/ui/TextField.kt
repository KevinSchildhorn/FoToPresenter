package me.kevinschildhorn.android.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import me.kevinschildhorn.android.ui.logic.Icon
import me.kevinschildhorn.common.viewmodel.EmailValidationViewModel


/*
@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun SampleTextField(
    dogsState: BreedViewState,
    update:(String) -> Unit,
) {

    OutlinedTextField(
        value = dogsState.text,
        onValueChange = { newValue ->
            update(newValue)
        }
    )


/*
    Column {
        if(viewModel.userNameHasLocalError) {
            Text(
                text = "Error",
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
        OutlinedTextField(
            value = text,
            onValueChange = {
                viewModel.updateUsername(it)
                //viewModel.username.value = it
                //viewModel.validate(it)
                //viewModel.validate(it)
                //viewModel.verifyEmail(it)
                //textFieldState.updateWithState(viewModel.validationState.value, it)
            },
            label = { Text("state.value.hint") },
            trailingIcon = {
                //viewModel.emailUiState.value.trailingIconState.Icon()
            },
            modifier = Modifier.onFocusChanged {

            }
        )
    }*/
}*/