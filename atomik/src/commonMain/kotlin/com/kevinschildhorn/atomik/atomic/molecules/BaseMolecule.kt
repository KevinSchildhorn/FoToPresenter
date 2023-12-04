package com.kevinschildhorn.atomik.atomic.molecules

import com.kevinschildhorn.atomik.atomic.atoms.Atom

/**
 * Represents a Molecule from Atomic Design, as an interface
 * https://atomicdesign.bradfrost.com/
 *
 * An abstract class that represents a molecule. A molecule consists of multiple atoms to create a
 * robust UI element. This Base molecule contains an array of atoms used, and can be inherited for
 * more specific functionality.
 */
public abstract class BaseMolecule {
    public abstract val atoms: List<Atom>
}
