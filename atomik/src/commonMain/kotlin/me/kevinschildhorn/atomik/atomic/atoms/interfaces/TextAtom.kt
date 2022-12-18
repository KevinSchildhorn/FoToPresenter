package me.kevinschildhorn.atomik.atomic.atoms.interfaces

import me.kevinschildhorn.atomik.color.Color
import me.kevinschildhorn.atomik.typography.base.AtomikTypography

interface TextAtom {
    val textColor: Color
    val font: AtomikTypography
}
