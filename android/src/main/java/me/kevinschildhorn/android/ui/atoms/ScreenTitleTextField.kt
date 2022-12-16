package me.kevinschildhorn.android.ui.atoms

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import me.kevinschildhorn.common.design.theme.atomic.DesignAtoms

@Composable
fun ScreenTitleTextField(
    text: String
) {
    //val style = DesignAtoms.TextView.screenTitle
    Text(
        text = text,
        style = MaterialTheme.typography.h3,
    )
}
