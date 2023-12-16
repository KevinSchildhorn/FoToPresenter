package com.kevinschildhorn.fotopresenter.ui.screens.common.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.kevinschildhorn.atomik.color.base.composeColor
import com.kevinschildhorn.fotopresenter.ui.atoms.FotoColors
import com.kevinschildhorn.fotopresenter.ui.screens.common.CommonAtoms

@Composable
fun FotoDialog(
    dialogTitle: String,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    content: @Composable (() -> Unit)?,
) {
    AlertDialog(
        modifier = Modifier.clip(RoundedCornerShape(10.dp)),
        backgroundColor = FotoColors.surface.composeColor,
        title = {
            AtomikText(
                text = dialogTitle,
                atom = CommonAtoms.dialogTitle
            )
        },
        text = content,
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            PrimaryTextButton("Confirm"){
                onConfirmation()
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                AtomikText("Cancel", atom = CommonAtoms.dialogButton)
            }
        }
    )
}