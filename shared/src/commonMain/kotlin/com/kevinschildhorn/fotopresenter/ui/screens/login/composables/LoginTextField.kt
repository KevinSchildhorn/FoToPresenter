package com.kevinschildhorn.fotopresenter.ui.screens.login.composables

import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kevinschildhorn.atomik.atomic.atoms.shape
import com.kevinschildhorn.atomik.atomic.atoms.textStyle
import com.kevinschildhorn.fotopresenter.ui.screens.login.LoginScreenAtoms

@Composable
fun LoginTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
) {
    val molecule = LoginScreenAtoms.textFieldMolecule
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
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
    )
}
