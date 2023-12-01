package com.kevinschildhorn.atomik.atomic.molecules

import androidx.compose.material.ContentAlpha
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults.textFieldColors
import androidx.compose.runtime.Composable
import com.kevinschildhorn.atomik.color.base.composeColor

@Composable
public fun TextFieldMolecule.colors(): TextFieldColors {
    val textColor = textAtom.textColor.composeColor
    val backgroundColor = backgroundColorAtom.color.composeColor
    val disabledBackgroundColor = backgroundColor.copy(alpha = ContentAlpha.disabled)
    val errorTextColor = textAtom.textColor.composeColor

    return textFieldColors(
        textColor = textColor,
        backgroundColor = backgroundColor,
        placeholderColor = hintTextAtom?.textColor?.composeColor ?: backgroundColor.copy(ContentAlpha.medium),
        cursorColor = cursorColor?.composeColor ?: textColor,

        disabledTextColor = disabledColorAtom?.color?.composeColor ?: textColor.copy(ContentAlpha.disabled),
        disabledIndicatorColor = disabledColorAtom?.color?.composeColor ?: disabledBackgroundColor,
        disabledLabelColor = disabledColorAtom?.color?.composeColor ?: disabledBackgroundColor,
        disabledLeadingIconColor = disabledColorAtom?.color?.composeColor ?: disabledBackgroundColor,
        disabledPlaceholderColor = disabledColorAtom?.color?.composeColor ?: disabledBackgroundColor,
        disabledTrailingIconColor = disabledColorAtom?.color?.composeColor ?: disabledBackgroundColor,

        errorCursorColor = errorColor?.composeColor ?: errorTextColor,
        errorIndicatorColor = errorColor?.composeColor ?: errorTextColor,
        errorLabelColor = errorColor?.composeColor ?: errorTextColor,
        errorLeadingIconColor = errorColor?.composeColor ?: errorTextColor,
        errorTrailingIconColor = errorColor?.composeColor ?: errorTextColor,
    )
}