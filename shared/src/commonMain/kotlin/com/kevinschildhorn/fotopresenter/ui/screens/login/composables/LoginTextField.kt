package com.kevinschildhorn.fotopresenter.ui.screens.login.composables

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.KeyboardOptions.Companion
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import com.kevinschildhorn.fotopresenter.ui.atoms.FotoTypography
import com.kevinschildhorn.fotopresenter.ui.atoms.disabled
import com.kevinschildhorn.fotopresenter.ui.atoms.fotoColors
import com.kevinschildhorn.fotopresenter.ui.atoms.fotoShapes

@Composable
fun LoginTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        visualTransformation = visualTransformation,
        placeholder = {
            Text(
                placeholder,
                color = fotoColors.onSurface,
                style = FotoTypography().subtitle1,
            )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = fotoColors.onPrimary,
            disabledTextColor = disabled,
        ),
        shape = fotoShapes.small,
        textStyle = FotoTypography().subtitle1,
        keyboardOptions = keyboardOptions,
        trailingIcon = trailingIcon,
        modifier = modifier,
    )
}
