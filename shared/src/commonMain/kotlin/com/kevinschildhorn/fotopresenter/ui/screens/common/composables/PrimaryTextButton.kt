package com.kevinschildhorn.fotopresenter.ui.screens.common.composables


import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kevinschildhorn.fotopresenter.ui.atoms.FotoTypography
import com.kevinschildhorn.fotopresenter.ui.atoms.fotoColors

@Composable
fun PrimaryTextButton(
    title: String,
    modifier: Modifier = Modifier,
    buttonState: ButtonState = ButtonState.ENABLED,
    onClick: () -> Unit,
) {

    PrimaryButton(modifier = modifier, buttonState = buttonState, onClick = onClick) {
        Text(
            title,
            style = FotoTypography().button,
            color = fotoColors.onPrimary,
        )
    }
}
