package me.kevinschildhorn.atomik

import me.kevinschildhorn.atomik.atomic.atoms.Atom
import me.kevinschildhorn.atomik.color.CustomColorSet
import me.kevinschildhorn.atomik.color.DefaultColorSet
import me.kevinschildhorn.atomik.color.base.AtomikColor
import me.kevinschildhorn.atomik.color.base.ColorSet
import me.kevinschildhorn.atomik.typography.CustomTypographySet
import me.kevinschildhorn.atomik.typography.PlatformTypographySet
import me.kevinschildhorn.atomik.typography.base.TypographySet

open class DesignSystem(
    open val colorSet: ColorSet,
    open val typographySet: TypographySet,
    open val atoms: Map<String, Atom>,
)

class PlatformDesignSystem(
    override val colorSet: DefaultColorSet,
    override val typographySet: PlatformTypographySet,
    override val atoms: Map<String, Atom>,
) : DesignSystem(colorSet, typographySet, atoms)

class CustomDesignSystem(
    override val colorSet: CustomColorSet,
    override val typographySet: CustomTypographySet,
    override val atoms: Map<String, Atom>,
) : DesignSystem(colorSet, typographySet, atoms)