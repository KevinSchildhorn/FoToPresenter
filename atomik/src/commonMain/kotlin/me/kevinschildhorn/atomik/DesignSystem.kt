package me.kevinschildhorn.atomik

import me.kevinschildhorn.atomik.atomic.atoms.Atom
import me.kevinschildhorn.atomik.color.base.AtomikColor
import me.kevinschildhorn.atomik.typography.base.TypographySet

class DesignSystem(
    val colorSet: Map<String, AtomikColor>,
    val typographySet: TypographySet,
    val atoms: Map<String, Atom>,
)