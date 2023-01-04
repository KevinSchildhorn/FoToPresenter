package me.kevinschildhorn.atomik.atomic.atoms

import androidx.compose.foundation.layout.height
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import me.kevinschildhorn.atomik.atomic.atoms.interfaces.FixedSizeAtom

val FixedSizeAtom.heightModifier: Modifier
    get() = height?.let {
        Modifier.height(it.dp)
    } ?: Modifier