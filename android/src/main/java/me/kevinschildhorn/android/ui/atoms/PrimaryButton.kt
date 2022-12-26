package me.kevinschildhorn.android.ui.atoms

import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import me.kevinschildhorn.android.extensions.buttonColors
import me.kevinschildhorn.android.fontFamily
import me.kevinschildhorn.atomik.atomic.atoms.TextButtonAtom
import me.kevinschildhorn.atomik.atomic.atoms.textStyle
import me.kevinschildhorn.common.design.theme.atomic.DesignAtoms

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
) {
    val atom: TextButtonAtom = DesignAtoms.Buttons.primaryButtonAtom
    TextButton(
        onClick = onClick,
        colors = atom.enabledColor.buttonColors(),
    ) {
        Text(
            text,
            style = atom.textStyle(fontFamily)
        )
    }
}

@Preview
@Composable
fun DefaultPreview() {
    PrimaryButton(
        "Hello World"
    ){
        print("Hello")
    }
}