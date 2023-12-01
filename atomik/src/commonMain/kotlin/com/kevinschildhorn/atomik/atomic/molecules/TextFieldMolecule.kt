package com.kevinschildhorn.atomik.atomic.molecules

import com.kevinschildhorn.atomik.atomic.atoms.Atom
import com.kevinschildhorn.atomik.atomic.atoms.interfaces.ColorAtom
import com.kevinschildhorn.atomik.atomic.atoms.interfaces.TextAtom
import com.kevinschildhorn.atomik.color.base.AtomikColor

/**
 * A molecule that contains information about a TextField
 *
 * @property textAtom the [TextAtom] used for the entered Text
 * @property hintTextAtom the [TextAtom] used for the hint Text when the field is empty
 * @property errorTextAtom the [TextAtom] used for the error Text when the field has an error
 * @property disabledColorAtom the [ColorAtom] of the text if the ui is disabled
 * @property backgroundColorAtom the [ColorAtom] of the background
 * @property cursorColor the [AtomikColor] of the cursor
 * @property errorColor the [AtomikColor] of the error, used for all error elements in the View
 */
public class TextFieldMolecule(
    public val textAtom: TextAtom,
    public val backgroundColorAtom: ColorAtom,
    public val hintTextAtom: TextAtom? = null,
    public val errorTextAtom: TextAtom? = null,
    public val disabledColorAtom: ColorAtom? = null,
    public val cursorColor: AtomikColor? = null,
    public val errorColor: AtomikColor? = errorTextAtom?.textColor,
) : BaseMolecule() {

    override val atoms: List<Atom>
        get() = listOf(/*textAtom, hintTextAtom, errorTextAtom, cursorColor*/)
}

