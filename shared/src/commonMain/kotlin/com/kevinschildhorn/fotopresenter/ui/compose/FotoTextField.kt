package com.kevinschildhorn.fotopresenter.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.kevinschildhorn.atomik.atomic.atoms.shape
import com.kevinschildhorn.atomik.atomic.atoms.textStyle
import com.kevinschildhorn.atomik.atomic.molecules.colors
import com.kevinschildhorn.fotopresenter.ui.atoms.LoginScreenAtoms

@Composable
fun FotoTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
) {
    val molecule = LoginScreenAtoms.textFieldMolecule
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                placeholder,
                style = molecule.hintTextAtom?.textStyle ?: molecule.textAtom.textStyle
            )
        },
        colors = molecule.colors(),
        shape = molecule.shape,
        textStyle = molecule.textAtom.textStyle
    )
}

@Composable
fun FotoPasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
) {
    val molecule = LoginScreenAtoms.textFieldMolecule

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        visualTransformation = PasswordVisualTransformation(),
        placeholder = {
            Text(
                placeholder,
                style = molecule.hintTextAtom?.textStyle ?: molecule.textAtom.textStyle
            )
        },
        colors = molecule.colors(),
        shape = molecule.shape,
        textStyle = molecule.textAtom.textStyle
    )
}