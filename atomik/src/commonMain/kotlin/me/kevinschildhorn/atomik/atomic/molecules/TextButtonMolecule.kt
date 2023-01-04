package me.kevinschildhorn.atomik.atomic.molecules

import me.kevinschildhorn.atomik.atomic.atoms.Atom
import me.kevinschildhorn.atomik.atomic.atoms.ButtonAtom
import me.kevinschildhorn.atomik.atomic.atoms.TextViewAtom
import me.kevinschildhorn.atomik.color.base.AtomikColor
import me.kevinschildhorn.atomik.color.base.AtomikEnabledColor
import me.kevinschildhorn.atomik.typography.base.AtomikFontFamily
import me.kevinschildhorn.atomik.typography.base.AtomikTypography

class TextButtonMolecule(
    color: AtomikColor,
    disabledColor: AtomikColor,
    radius: Int = 0,
    height: Int?,
    textColor: AtomikColor,
    typography: AtomikTypography,
    fontFamily: AtomikFontFamily?,
) : BaseMolecule() {

    val buttonAtom = ButtonAtom(
        enabledColor = AtomikEnabledColor(color, disabledColor),
        radius = radius,
        height = height
    )

    val textAtom = TextViewAtom(
        textColor = textColor,
        typography = typography,
        fontFamily = fontFamily,
    )


    override val atoms: List<Atom> = listOf(buttonAtom, textAtom)
}