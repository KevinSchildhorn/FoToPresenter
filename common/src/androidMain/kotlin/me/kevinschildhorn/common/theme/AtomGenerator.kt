package me.kevinschildhorn.common.theme

import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import me.kevinschildhorn.common.atomic.atoms.Atom
import me.kevinschildhorn.common.atomic.atoms.interfaces.AtomType
import me.kevinschildhorn.common.atomic.atoms.interfaces.EnablableAtom
import me.kevinschildhorn.common.ui.SharedEnabledColor

object AtomGenerator {

    @Composable
    fun GenerateAtom(atom: Atom): Unit {
        val modifier = Modifier.apply {
            atom.height?.let {
                this.height(it.dp)
            }

        }
        return when (atom.type) {
            AtomType.BUTTON -> Button(
                onClick = {},
                content = {},
                colors =
                (atom as? EnablableAtom)?.let {
                    it.enabledColor.asComposable()
                } ?: ButtonDefaults.buttonColors()

            )
            AtomType.TEXT -> Text(text = "", color = atom.color.androidColor)
        }
    }

}

@Composable
fun SharedEnabledColor.asComposable(): ButtonColors =
    ButtonDefaults.buttonColors(
        backgroundColor = this.color.androidColor,
        disabledBackgroundColor = this.disabledColor.androidColor
    )