package com.kevinschildhorn.atomik.atomic.atoms.interfaces

import com.kevinschildhorn.atomik.atomic.atoms.Atom
import com.kevinschildhorn.atomik.color.base.AtomikColor
import com.kevinschildhorn.atomik.typography.base.AtomikFontFamily
import com.kevinschildhorn.atomik.typography.base.AtomikTypography
import com.kevinschildhorn.atomik.typography.base.AtomikTypographyType

/**
 * An atom that has text
 *
 * @property textColor the color of the text
 * @property typography the typography associated with the text
 * @property fontFamily the fontFamily associated with the text
 */
public interface TextAtom : AtomInterface {
    public val textColor: AtomikColor
    public val typography: AtomikTypography
    public val fontFamily: AtomikFontFamily?
}

/**
 * Convenience function to get the atom as a text atom
 */
public val Atom.textAtom: TextAtom?
    get() = this.asAtom()

/**
 * Convenience class to only get the Text Atom
 */
public class SimpleTextAtom(
    override val textColor: AtomikColor,
    override val typography: AtomikTypography,
    override val fontFamily: AtomikFontFamily?
) : Atom(), TextAtom {
    override val type: AtomType = AtomType.TEXT
}