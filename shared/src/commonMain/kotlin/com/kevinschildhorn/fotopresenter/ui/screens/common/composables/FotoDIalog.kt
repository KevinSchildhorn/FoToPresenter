package com.kevinschildhorn.fotopresenter.ui.screens.common.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.kevinschildhorn.fotopresenter.ui.atoms.Padding

@Composable
fun FotoDialog(
    dialogTitle: String,
    onDismissRequest: () -> Unit,
    onConfirmation: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        // Draw a rectangle shape with rounded corners inside the dialog
        Card(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier =
                    Modifier
                        .fillMaxWidth().padding(Padding.STANDARD.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
            ) {
                DialogTitle(text = dialogTitle)
                Spacer(Modifier.size(Padding.MEDIUM.dp))
                content()
                Row(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(top = Padding.SMALL.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    TextButton(
                        onClick = {
                            onDismissRequest()
                        },
                        modifier = Modifier.padding(Padding.SMALL.dp),
                    ) {
                        DialogButtonText("Dismiss", modifier = Modifier.testTag("Dismiss"))
                    }
                    onConfirmation?.let {
                        PrimaryTextButton("Confirm", modifier = Modifier.testTag("Confirm")) {
                            it()
                        }
                    }
                }
            }
        }
    }
}
