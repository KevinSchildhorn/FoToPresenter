package me.kevinschildhorn.atomik.atomic.atoms.interfaces

import me.kevinschildhorn.atomik.color.base.AtomikColor
import me.kevinschildhorn.atomik.typography.base.AtomikTypography

interface TextAtom {
    val textColor: AtomikColor
    val font: AtomikTypography
}
