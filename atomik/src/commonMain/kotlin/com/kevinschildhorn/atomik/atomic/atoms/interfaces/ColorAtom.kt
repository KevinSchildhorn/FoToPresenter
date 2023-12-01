package com.kevinschildhorn.atomik.atomic.atoms.interfaces

import com.kevinschildhorn.atomik.atomic.atoms.Atom
import com.kevinschildhorn.atomik.color.base.AtomikColor

/**
 * An atom that contains information about the color of the UI
 *
 * @property color the color of the UI.
 */
public interface ColorAtom : AtomInterface {
    public val color: AtomikColor
}

/**
 * Convenience function to get the atom as a color atom
 */
internal val Atom.colorAtom: ColorAtom?
    get() = this.asAtom()

/**
 * Convenience class to only get the Color Atom
 */
public class SimpleColorAtom(override val color: AtomikColor) : Atom(), ColorAtom {
    override val type: AtomType = AtomType.VIEW
}
