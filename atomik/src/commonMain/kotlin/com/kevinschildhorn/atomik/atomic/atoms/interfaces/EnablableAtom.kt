package com.kevinschildhorn.atomik.atomic.atoms.interfaces

import com.kevinschildhorn.atomik.atomic.atoms.Atom
import com.kevinschildhorn.atomik.color.base.AtomikColorType

/**
 * An atom that can be enabled / disabled. Contains the disabled color of the UI and inherits [ColorAtom]
 *
 * @property disabledColor the disabled color of the UI.
 */
internal interface EnablableAtom : ColorAtom {
    public val disabledColor: AtomikColorType?
}

/**
 * Convenience function to get the atom as an enabled atom
 */
internal val Atom.enablableAtom: EnablableAtom?
    get() = this.asAtom()
