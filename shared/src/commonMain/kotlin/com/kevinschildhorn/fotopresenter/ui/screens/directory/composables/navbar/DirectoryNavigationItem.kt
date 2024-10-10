package com.kevinschildhorn.fotopresenter.ui.screens.directory.composables.navbar

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.kevinschildhorn.fotopresenter.ui.atoms.fotoColors
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.DialogButtonText

@Composable
fun DirectoryNavigationItem(title: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.height(44.dp).clip(RoundedCornerShape(10.dp)),
        colors =
        ButtonDefaults.buttonColors(
            backgroundColor = fotoColors.secondary,
            disabledBackgroundColor = fotoColors.onSecondary,
        ),
    ) {
        DialogButtonText(title)
    }
}