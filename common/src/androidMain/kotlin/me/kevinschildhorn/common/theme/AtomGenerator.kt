package me.kevinschildhorn.common.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import me.kevinschildhorn.common.atomic.atoms.Atom
import me.kevinschildhorn.common.atomic.atoms.TextFieldAtom
import me.kevinschildhorn.common.atomic.atoms.interfaces.AtomType
import me.kevinschildhorn.common.atomic.atoms.interfaces.EnablableAtom
import me.kevinschildhorn.common.color.SharedEnabledColor

object AtomGenerator {

    @Composable
    fun GenerateAtom(atom: Atom): Unit {
        val modifier = Modifier.apply {
        }
        return when (atom.type) {
            AtomType.BUTTON -> Button(
                onClick = {},
                content = {},
                colors =
                (atom as? EnablableAtom)?.enabledColor?.asComposable() ?: ButtonDefaults.buttonColors()
            )
            //AtomType.TEXT -> Text(text = "", color = atom.color.androidColor)
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