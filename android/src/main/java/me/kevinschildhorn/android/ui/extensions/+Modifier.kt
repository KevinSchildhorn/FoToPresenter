package me.kevinschildhorn.android.ui.extensions

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import me.kevinschildhorn.android.util.safeLet
import me.kevinschildhorn.common.ui.atomic.atoms.Atom
import me.kevinschildhorn.common.ui.atomic.atoms.FigmaAtom

fun Modifier.Companion.fromAtom(atom: FigmaAtom): Modifier {
    var modifier = Modifier.clip(RoundedCornerShape(atom.cornerRadius.dp))

    if (atom.constrainProportions) {
        safeLet(atom.width, atom.height) { width, height ->
            modifier = modifier.aspectRatio(width.toFloat() / height.toFloat())
        }
    } else {
        atom.width?.let { width ->
            modifier = modifier.width(width.dp)
        }
        atom.height?.let { height ->
            modifier = modifier.height(height.dp)
        }
    }
    modifier = modifier.padding(horizontal = atom.marginX.dp, vertical = atom.marginY.dp)
    return modifier
}

