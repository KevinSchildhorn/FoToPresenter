package com.kevinschildhorn.fotopresenter.ui.compose.login

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.kevinschildhorn.atomik.atomic.atoms.shape
import com.kevinschildhorn.atomik.atomic.atoms.textStyle
import com.kevinschildhorn.fotopresenter.ui.atoms.LoginScreenAtoms
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.fill.Eye
import compose.icons.evaicons.fill.EyeOff

@Composable
fun LoginPasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
) {
    val molecule = LoginScreenAtoms.textFieldMolecule
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        placeholder = {
            Text(
                placeholder,
                style = molecule.hintTextAtom?.textStyle ?: molecule.textAtom.textStyle,
            )
        },
        colors = molecule.colors(),
        shape = molecule.shape,
        textStyle = molecule.textAtom.textStyle,
        modifier = modifier,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            val image =
                if (passwordVisible) {
                    EvaIcons.Fill.Eye
                } else {
                    EvaIcons.Fill.EyeOff
                }

            val description = if (passwordVisible) "Hide password" else "Show password"

            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(imageVector = image, description)
            }
        },
    )
}
