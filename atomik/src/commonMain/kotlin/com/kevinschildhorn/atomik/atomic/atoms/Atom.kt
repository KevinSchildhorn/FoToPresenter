package com.kevinschildhorn.atomik.atomic.atoms

import com.kevinschildhorn.atomik.atomic.atoms.interfaces.AtomInterface
import com.kevinschildhorn.atomik.atomic.atoms.interfaces.AtomType

/**
 * Represents an Atom from Atomic Design, as a class
 * https://atomicdesign.bradfrost.com/
 *
 * An abstract class that represents an Atom. While interfaces are used to specify what Atoms conform to,
 * the [Atom] class provides the UI details. In Atomic Design, components contain subcomponents
 * that are related to the parent component. For example Organisms contain Molecules, which contain Atoms.
 *
 * @property type the Type of atom being displayed, such as button or text
 * @property subComponents the components related to this atom, as mentioned in description
 */
public abstract class Atom {
    public abstract val type: AtomType
    public open val subComponents: List<Atom> = emptyList()

    /**
     * Checks to see if the Atom has any subcomponents. Returns boolean
     */
    public val hasSubComponents: Boolean
        get() = subComponents.isNotEmpty()

    /**
     * Checks to see if this Atom implements a certain [AtomInterface] and if it does returns the interface
     * @return the atom as the interface
     */
    @Suppress("UNCHECKED_CAST")
    public fun <T : AtomInterface> asAtom(): T? = (this as? T)

    /**
     * Finds a subComponent matching a specified [AtomInterface] if it exists
     * @return the subComponent of a specified interface if it's found
     */
    public inline fun <reified T : AtomInterface> subAtom(): T? = subComponents.find { it is T } as? T
}
