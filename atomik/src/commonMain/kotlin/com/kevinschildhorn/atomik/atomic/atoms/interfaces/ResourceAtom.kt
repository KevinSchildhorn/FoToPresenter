package com.kevinschildhorn.atomik.atomic.atoms.interfaces

/**
 * An atom that has an image resource associated with it
 *
 * @property image the image associated with the atom
 */
internal interface ResourceAtom<T> : AtomInterface {
    public val image: T
}
