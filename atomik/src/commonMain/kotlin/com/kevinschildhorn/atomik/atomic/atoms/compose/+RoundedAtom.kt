@file:Suppress("ktlint:standard:filename")

package com.kevinschildhorn.atomik.atomic.atoms

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.kevinschildhorn.atomik.atomic.atoms.interfaces.RoundedAtom

public val RoundedAtom.shape: Shape
    get() = RoundedCornerShape(radius.dp)
