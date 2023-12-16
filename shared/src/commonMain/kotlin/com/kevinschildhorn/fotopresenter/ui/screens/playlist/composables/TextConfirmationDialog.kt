package com.kevinschildhorn.fotopresenter.ui.screens.playlist.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.kevinschildhorn.fotopresenter.ui.atoms.Padding
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.FotoDialog
import com.kevinschildhorn.fotopresenter.ui.screens.login.composables.LoginTextField

@Composable
fun TextConfirmationDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: (String) -> Unit,
) {
    var enteredValue:String by remember { mutableStateOf("") }

    FotoDialog(
        dialogTitle = "Playlist Name",
        onDismissRequest = onDismissRequest,
        onConfirmation = {
            onConfirmation(enteredValue)
        },
    ) {
        LoginTextField(
            enteredValue,
            onValueChange = {
                enteredValue = it
            },
            placeholder = "Name",
            modifier = Modifier.padding(top = Padding.STANDARD.dp)
        )
    }
}