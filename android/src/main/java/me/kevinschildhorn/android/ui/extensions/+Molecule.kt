package me.kevinschildhorn.android.ui.extensions

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import me.kevinschildhorn.android.util.AsComposable
import me.kevinschildhorn.android.util.modifiers
import me.kevinschildhorn.common.ui.atomic.atoms.AtomType
import me.kevinschildhorn.common.ui.atomic.molecules.Molecule

@Composable
fun Molecule.AsComposable(text: String) {
    when (type) {
        AtomType.BUTTON -> Button(modifier = design.modifiers, onClick = {}) {
            atoms.forEach {
                it.AsComposable(text)
            }
        }
        AtomType.TEXT -> Text(text = text, modifier = design.modifiers)
        AtomType.GROUP -> Box(modifier = design.modifiers) {
            atoms.forEach {
                it.AsComposable(text)
            }
        }
    }
}