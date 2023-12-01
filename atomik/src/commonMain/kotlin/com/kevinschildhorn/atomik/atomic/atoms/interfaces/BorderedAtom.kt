package com.kevinschildhorn.atomik.atomic.atoms.interfaces

import com.kevinschildhorn.atomik.ExperimentalAtomik
import com.kevinschildhorn.atomik.atomic.atoms.Atom
import com.kevinschildhorn.atomik.color.base.AtomikColor

/**
 * An atom that contains information about a border UI
 *
 * @property borderColor the color of the border surrounding the UI
 */
@OptIn(ExperimentalAtomik::class)
internal interface BorderedAtom : AtomInterface {
    public val borderColor: AtomikColor
}

/**
 * Convenience function to get the atom as a bordered atom
 */
internal val Atom.borderedAtom: BorderedAtom?
    get() = this.asAtom()
