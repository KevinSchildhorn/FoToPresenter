package com.kevinschildhorn.atomik.atomic.molecules

import com.kevinschildhorn.atomik.atomic.atoms.interfaces.BorderedAtom
import com.kevinschildhorn.atomik.atomic.atoms.interfaces.ColorAtom
import com.kevinschildhorn.atomik.atomic.atoms.interfaces.RoundedAtom
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
 * @property borderColor the [AtomikColor] of the border
 */
public class OutlinedTextFieldMolecule(
    textAtom: TextAtom,
    backgroundColorAtom: ColorAtom,
    hintTextAtom: TextAtom? = null,
    errorTextAtom: TextAtom? = null,
    disabledColorAtom: ColorAtom? = null,
    cursorColor: AtomikColor? = null,
    errorColor: AtomikColor? = errorTextAtom?.textColor,
    override val focusedBorderColor: AtomikColor,
    override val unFocusedBorderColor: AtomikColor = focusedBorderColor,
    override val radius: Int,
) : TextFieldMolecule(
    textAtom,
    backgroundColorAtom,
    hintTextAtom,
    errorTextAtom,
    disabledColorAtom,
    cursorColor,
    errorColor,
), BorderedAtom, RoundedAtom