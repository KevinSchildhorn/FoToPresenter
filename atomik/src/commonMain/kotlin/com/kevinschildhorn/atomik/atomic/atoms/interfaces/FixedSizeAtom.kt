package com.kevinschildhorn.atomik.atomic.atoms.interfaces

import com.kevinschildhorn.atomik.atomic.atoms.Atom

/**
 * An atom that has a fixed size, rather than a scalable size, such as a [ConstrainedAtom]
 *
 * @property width the width of the UI (in dp / pt)
 * @property height the height of the UI (in dp / pt)
 */
internal interface FixedSizeAtom : AtomInterface {
    public val width: Int?
    public val height: Int?
}

/**
 * Convenience function to get the atom as a fixed size atom
 */
internal val Atom.fixedSizeAtom: FixedSizeAtom?
    get() = this.asAtom()
