package me.kevinschildhorn.android.ui.atoms

import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import me.kevinschildhorn.android.fontFamily
import me.kevinschildhorn.atomik.atomic.atoms.textStyle
import me.kevinschildhorn.atomik.color.base.test123
import me.kevinschildhorn.common.design.theme.atomic.DesignAtoms

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
) {
    val atom = DesignAtoms.Buttons.primaryButtonAtom
    TextButton(
        onClick = onClick,
        colors = atom.enabledColor.test123(),
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