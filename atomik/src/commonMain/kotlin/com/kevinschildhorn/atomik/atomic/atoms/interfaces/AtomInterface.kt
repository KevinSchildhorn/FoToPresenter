package com.kevinschildhorn.atomik.atomic.atoms.interfaces

/**
 * Represents an Atom from Atomic Design, as an interface
 * https://atomicdesign.bradfrost.com/
 *
 * A common interface that represents an Atom. These interfaces are used to specify what the
 * Atoms must contain, and can be used to get specific information about an atom.
 * This is the basis of Atomik. Other Atoms inherit from this interface to add UI details to the
 * Atom, such as [ColorAtom] or [TextAtom]. Then a UI representation such as a [ButtonAtom]
 * implements these interfaces and provides the details to correctly display the UI.
 */
public interface AtomInterface
