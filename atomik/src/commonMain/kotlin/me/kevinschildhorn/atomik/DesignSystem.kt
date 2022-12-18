package me.kevinschildhorn.atomik

import me.kevinschildhorn.atomik.atomic.atoms.Atom
import me.kevinschildhorn.atomik.color.Color
import me.kevinschildhorn.atomik.typography.base.TypographySet

class DesignSystem(
    val colorSet: Map<String, Color>,
    val typographySet: TypographySet,
    val atoms: Map<String, Atom>,
)