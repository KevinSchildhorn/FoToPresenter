package com.kevinschildhorn.fotopresenter.ui.screens.common.composables


import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kevinschildhorn.atomik.atomic.atoms.textStyle
import com.kevinschildhorn.fotopresenter.ui.screens.login.LoginScreenAtoms

@Composable
fun PrimaryTextButton(
    title: String,
    modifier: Modifier = Modifier,
    buttonState: ButtonState = ButtonState.ENABLED,
    onClick: () -> Unit,
) {
    val molecule = LoginScreenAtoms.primaryButton

    PrimaryButton(modifier = modifier, buttonState = buttonState, onClick = onClick) {
        Text(
            title,
            style = molecule.textAtom.textStyle,
        )
    }
}
