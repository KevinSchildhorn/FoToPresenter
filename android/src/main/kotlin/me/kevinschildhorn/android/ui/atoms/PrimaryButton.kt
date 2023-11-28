package me.kevinschildhorn.android.ui.atoms

import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import me.kevinschildhorn.android.extensions.buttonColors
import me.kevinschildhorn.android.fontFamily
import me.kevinschildhorn.atomik.atomic.atoms.heightModifier
import me.kevinschildhorn.atomik.atomic.atoms.shape
import me.kevinschildhorn.atomik.atomic.atoms.textStyle
import me.kevinschildhorn.atomik.atomic.molecules.TextButtonMolecule
import me.kevinschildhorn.common.design.theme.atomic.DesignAtoms
/*
@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
) {
    val molecule: TextButtonMolecule = DesignAtoms.Buttons.primaryButtonMolecule
    val buttonAtom = molecule.buttonAtom
    TextButton(
        onClick = onClick,
        colors = buttonAtom.enabledColor.buttonColors(),
        shape = buttonAtom.shape,
        modifier = buttonAtom.heightModifier
    ) {
        val textAtom = molecule.textAtom
        Text(
            text,
            color = textAtom.textColor.platformColor,
            style = textAtom.textStyle(fontFamily),
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
}*/