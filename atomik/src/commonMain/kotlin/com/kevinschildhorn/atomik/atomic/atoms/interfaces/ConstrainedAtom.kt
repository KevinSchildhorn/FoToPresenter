package com.kevinschildhorn.atomik.atomic.atoms.interfaces

import com.kevinschildhorn.atomik.atomic.atoms.Atom

/**
 * An atom that contains information about the constraints of the UI(i.e. alignment)
 *
 * @property constraintX the constraint in the X Axis (horizontal)
 * @property constraintY the constraint in the Y Axis (vertical)
 */
public interface ConstrainedAtom : AtomInterface {
    public val constraintX: AtomikConstraintX
    public val constraintY: AtomikConstraintY
}

/**
 * An enum that represents the horizontal constraint of the Atom
 */
public enum class AtomikConstraintX {
    CENTER,
    ALIGN_LEFT,
    ALIGN_RIGHT,
    SCALE
}

/**
 * An enum that represents the vertical constraint of the Atom
 */
public enum class AtomikConstraintY {
    CENTER,
    ALIGN_TOP,
    ALIGN_BOTTOM,
    SCALE
}

/**
 * Convenience function to get the atom as a constrained atom
 */
internal val Atom.constrainedAtom: ConstrainedAtom?
    get() = this.asAtom<ConstrainedAtom>()
