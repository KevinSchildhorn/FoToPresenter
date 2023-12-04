package com.kevinschildhorn.fotopresenter.ui.compose.common

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kevinschildhorn.atomik.atomic.atoms.textStyle
import com.kevinschildhorn.atomik.color.base.composeColor
import com.kevinschildhorn.fotopresenter.ui.atoms.LoginScreenAtoms.primaryButton

@Composable
fun PrimaryButton(
    title: String,
    modifier: Modifier = Modifier,
    buttonState: ButtonState = ButtonState.ENABLED,
    onClick: () -> Unit,
) {
    val molecule = primaryButton
    Button(
        onClick = onClick,
        modifier = modifier.height(44.dp),
        enabled = buttonState.enabled,
        colors =
        ButtonDefaults.buttonColors(
            backgroundColor = molecule.color.composeColor,
            disabledBackgroundColor = molecule.disabledColor.composeColor,
        ),
    ) {
        if (buttonState.loading) {
            CircularProgressIndicator(
                modifier = Modifier.width(30.dp),
                color = molecule.textAtom.textColor.composeColor,
            )
        } else {
            Text(
                title,
                style = molecule.textAtom.textStyle,
            )
        }
    }
}

enum class ButtonState {
    ENABLED,
    DISABLED,
    LOADING,
    ;

    val enabled: Boolean
        get() = this != DISABLED

    val loading: Boolean
        get() = this == LOADING
}
