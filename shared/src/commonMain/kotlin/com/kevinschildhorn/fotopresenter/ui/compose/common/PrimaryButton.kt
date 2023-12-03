package com.kevinschildhorn.fotopresenter.ui.compose.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kevinschildhorn.atomik.atomic.atoms.textStyle
import com.kevinschildhorn.atomik.color.base.composeColor
import com.kevinschildhorn.fotopresenter.ui.atoms.FotoColors
import com.kevinschildhorn.fotopresenter.ui.atoms.FotoTypography
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
        colors = ButtonDefaults.buttonColors(
            backgroundColor = molecule.color.composeColor,
            disabledBackgroundColor = molecule.disabledColor.composeColor,
        ),
    ) {
        if (buttonState.loading)
            CircularProgressIndicator(
                modifier = Modifier.width(30.dp),
                color = molecule.textAtom.textColor.composeColor,
            )
        else
            Text(
                title,
                style = molecule.textAtom.textStyle,
            )
    }
}

enum class ButtonState {
    ENABLED,
    DISABLED,
    LOADING;

    val enabled: Boolean
        get() = this != DISABLED

    val loading: Boolean
        get() = this == LOADING
}