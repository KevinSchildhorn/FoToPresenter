package me.kevinschildhorn.common.atomic.molecules

import me.kevinschildhorn.common.atomic.atoms.Atom
import me.kevinschildhorn.common.atomic.atoms.ImageAtom
import me.kevinschildhorn.common.atomic.atoms.TextAtom
import me.kevinschildhorn.common.atomic.atoms.TextFieldAtom
import me.kevinschildhorn.common.theme.DesignColors

class TextFieldMolecule(private val hint:String) : BaseMolecule() {

    val errorView = TextAtom("", DesignColors.textColor),
    val textEntry = TextFieldAtom(hint, DesignColors.textColor, DesignColors.primary),
    val rightIcon = ImageAtom(DesignColors.primary)

    override val atoms: List<Atom>
        get() = listOf(errorView,textEntry,rightIcon)
}