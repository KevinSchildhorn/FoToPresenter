package com.kevinschildhorn.atomik.atomic.atoms.interfaces

import com.kevinschildhorn.atomik.atomic.atoms.Atom

/**
 * An atom that can has padding surrounding it.
 *
 * This atom can take either specific padding(i.e. left, right, top, bottom),
 * or more generalized padding (vertical, horizontal, overall)
 *
 * Padding that is not set defaults back to a more generalized padding
 * For example: paddingBottom -> paddingVertical -> padding
 *
 * @property padding the horizontal padding of the atom (in dp/pt)
 * @property paddingHorizontal the horizontal padding of the atom (in dp/pt)
 * @property paddingVertical the vertical padding of the atom (in dp/pt)
 * @property paddingLeft the padding on the left of the atom (in dp/pt)
 * @property paddingRight the padding on the right of the atom (in dp/pt)
 * @property paddingTop the padding on the top of the atom (in dp/pt)
 * @property paddingBottom the padding on the bottom of the atom (in dp/pt)
 */
internal interface PaddingAtom : AtomInterface {
    public val padding: Int?
    public val paddingHorizontal: Int?
    public val paddingVertical: Int?
    public val paddingLeft: Int?
    public val paddingRight: Int?
    public val paddingTop: Int?
    public val paddingBottom: Int?
}

/**
 * Convenience function to get the atom as a padding atom
 */
internal val Atom.paddingAtom: PaddingAtom?
    get() = this.asAtom()
