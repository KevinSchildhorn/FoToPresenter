package com.kevinschildhorn.atomik.atomic.molecules

import com.kevinschildhorn.atomik.atomic.atoms.Atom
import com.kevinschildhorn.atomik.atomic.atoms.interfaces.EnablableAtom
import com.kevinschildhorn.atomik.atomic.atoms.interfaces.RoundedAtom
import com.kevinschildhorn.atomik.atomic.atoms.interfaces.TextAtom
import com.kevinschildhorn.atomik.color.base.AtomikColor

public class TextButtonMolecule(
    override val color: AtomikColor,
    override val disabledColor: AtomikColor,
    override val radius: Int,
    public val textAtom: TextAtom,
) : BaseMolecule(), EnablableAtom, RoundedAtom {
    override val atoms: List<Atom> = listOf(textAtom as Atom)
}
