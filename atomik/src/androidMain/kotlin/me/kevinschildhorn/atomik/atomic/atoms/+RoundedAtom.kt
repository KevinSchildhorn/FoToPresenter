package me.kevinschildhorn.atomik.atomic.atoms

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import me.kevinschildhorn.atomik.atomic.atoms.interfaces.RoundedAtom

val RoundedAtom.shape: Shape
    get() = RoundedCornerShape(radius.dp)