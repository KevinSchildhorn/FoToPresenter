package me.kevinschildhorn.common.design.theme

import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import me.kevinschildhorn.common.deprecated.atomic.atoms.Atom
import me.kevinschildhorn.common.deprecated.atomic.atoms.interfaces.AtomType
import me.kevinschildhorn.common.deprecated.atomic.atoms.interfaces.EnablableAtom
import me.kevinschildhorn.common.design.color.SharedEnabledColor

object AtomGenerator {

    @Composable
    fun GenerateAtom(atom: Atom) {
        val modifier = Modifier.apply {
        }
        return when (atom.type) {
            AtomType.BUTTON -> Button(
                onClick = {},
                content = {},
                colors =
                (atom as? EnablableAtom)?.enabledColor?.asComposable() ?: ButtonDefaults.buttonColors()
            )
            // AtomType.TEXT -> Text(text = "", color = atom.color.androidColor)
            else -> {}
        }
    }
}

@Composable
fun SharedEnabledColor.asComposable(): ButtonColors =
    ButtonDefaults.buttonColors(
        backgroundColor = this.color.androidColor,
        disabledBackgroundColor = this.disabledColor.androidColor
    )