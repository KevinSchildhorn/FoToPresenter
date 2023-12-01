package com.kevinschildhorn.atomik.atomic.molecules

import androidx.compose.material.ContentAlpha
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import com.kevinschildhorn.atomik.color.base.composeColor

@Composable
public fun OutlinedTextFieldMolecule.colors(): TextFieldColors {
    val textColor = textAtom.textColor.composeColor
    val backgroundColor = backgroundColorAtom.color.composeColor
    val disabledBackgroundColor = backgroundColor.copy(alpha = ContentAlpha.disabled)
    val errorTextColor = textAtom.textColor.composeColor

    return TextFieldDefaults.outlinedTextFieldColors(
        textColor = textColor,
        backgroundColor = backgroundColor,
        placeholderColor = hintTextAtom?.textColor?.composeColor ?: backgroundColor.copy(
            ContentAlpha.medium
        ),
        cursorColor = cursorColor?.composeColor ?: textColor,
        focusedBorderColor = borderColor.composeColor,

        disabledTextColor = disabledColorAtom?.color?.composeColor
            ?: textColor.copy(ContentAlpha.disabled),
        disabledBorderColor = disabledColorAtom?.color?.composeColor ?: disabledBackgroundColor,
        disabledLabelColor = disabledColorAtom?.color?.composeColor ?: disabledBackgroundColor,
        disabledLeadingIconColor = disabledColorAtom?.color?.composeColor
            ?: disabledBackgroundColor,
        disabledPlaceholderColor = disabledColorAtom?.color?.composeColor
            ?: disabledBackgroundColor,
        disabledTrailingIconColor = disabledColorAtom?.color?.composeColor
            ?: disabledBackgroundColor,

        errorCursorColor = errorColor?.composeColor ?: errorTextColor,
        errorBorderColor = errorColor?.composeColor ?: errorTextColor,
        errorLabelColor = errorColor?.composeColor ?: errorTextColor,
        errorLeadingIconColor = errorColor?.composeColor ?: errorTextColor,
        errorTrailingIconColor = errorColor?.composeColor ?: errorTextColor,
    )
}