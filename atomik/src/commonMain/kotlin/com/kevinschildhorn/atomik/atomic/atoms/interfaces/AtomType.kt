package com.kevinschildhorn.atomik.atomic.atoms.interfaces

import com.kevinschildhorn.atomik.ExperimentalAtomik

/**
 * The type of UI Element an atom is.
 * https://atomicdesign.bradfrost.com/
 *
 * This is used to determine what kind of UI to display based on the value
 */
@OptIn(ExperimentalAtomik::class)
public enum class AtomType {
    BUTTON,
    TEXT,
    IMAGE,
    TEXTFIELD,
    VIEW
}
