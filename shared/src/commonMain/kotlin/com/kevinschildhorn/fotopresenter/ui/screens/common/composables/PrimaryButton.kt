package com.kevinschildhorn.fotopresenter.ui.screens.common.composables

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kevinschildhorn.fotopresenter.ui.atoms.disabled
import androidx.compose.material.MaterialTheme

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    buttonState: ButtonState = ButtonState.ENABLED,
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(44.dp),
        enabled = buttonState.enabled,
        colors =
            ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.primary,
                disabledBackgroundColor = disabled,
            ),
    ) {
        if (buttonState.loading) {
            CircularProgressIndicator(
                modifier = Modifier.width(30.dp),
                color = MaterialTheme.colors.onPrimary,
            )
        } else {
            content()
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
