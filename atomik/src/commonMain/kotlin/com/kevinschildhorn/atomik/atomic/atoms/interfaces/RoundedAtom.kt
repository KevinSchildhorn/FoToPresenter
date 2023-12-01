package com.kevinschildhorn.atomik.atomic.atoms.interfaces

import com.kevinschildhorn.atomik.atomic.atoms.Atom

/**
 * An atom that has rounded edges
 *
 * @property radius the radius of the rounded edges (in dp / pt)
 */
internal interface RoundedAtom : AtomInterface {
    public val radius: Int
}

/**
 * Convenience function to get the atom as a rounded atom
 */
internal val Atom.roundedAtom: RoundedAtom?
    get() = this.asAtom()
