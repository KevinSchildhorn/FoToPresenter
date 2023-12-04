package com.kevinschildhorn.atomik.atomic.molecules

import androidx.compose.material.ContentAlpha
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import com.kevinschildhorn.atomik.atomic.atoms.interfaces.BorderedAtom
import com.kevinschildhorn.atomik.atomic.atoms.interfaces.ColorAtom
import com.kevinschildhorn.atomik.atomic.atoms.interfaces.RoundedAtom
import com.kevinschildhorn.atomik.atomic.atoms.interfaces.TextAtom
import com.kevinschildhorn.atomik.color.base.AtomikColor
import com.kevinschildhorn.atomik.color.base.composeColor

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
public open class OutlinedTextFieldMolecule(
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
),
    BorderedAtom,
    RoundedAtom {

    @Composable
    public fun colors(): TextFieldColors {
        val textColor = textAtom.textColor.composeColor
        val backgroundColor = backgroundColorAtom.color.composeColor
        val disabledBackgroundColor = backgroundColor.copy(alpha = ContentAlpha.disabled)
        val errorTextColor = textAtom.textColor.composeColor

        return TextFieldDefaults.outlinedTextFieldColors(
            textColor = textColor,
            backgroundColor = backgroundColor,
            placeholderColor =
            hintTextAtom?.textColor?.composeColor ?: backgroundColor.copy(
                ContentAlpha.medium,
            ),
            cursorColor = cursorColor?.composeColor ?: textColor,
            focusedBorderColor = focusedBorderColor.composeColor,
            unfocusedBorderColor = unFocusedBorderColor.composeColor,
            disabledTextColor =
            disabledColorAtom?.color?.composeColor
                ?: textColor.copy(ContentAlpha.disabled),
            disabledBorderColor = disabledColorAtom?.color?.composeColor ?: disabledBackgroundColor,
            disabledLabelColor = disabledColorAtom?.color?.composeColor ?: disabledBackgroundColor,
            disabledLeadingIconColor =
            disabledColorAtom?.color?.composeColor
                ?: disabledBackgroundColor,
            disabledPlaceholderColor =
            disabledColorAtom?.color?.composeColor
                ?: disabledBackgroundColor,
            disabledTrailingIconColor =
            disabledColorAtom?.color?.composeColor
                ?: disabledBackgroundColor,
            errorCursorColor = errorColor?.composeColor ?: errorTextColor,
            errorBorderColor = errorColor?.composeColor ?: errorTextColor,
            errorLabelColor = errorColor?.composeColor ?: errorTextColor,
            errorLeadingIconColor = errorColor?.composeColor ?: errorTextColor,
            errorTrailingIconColor = errorColor?.composeColor ?: errorTextColor,
        )
    }
}
