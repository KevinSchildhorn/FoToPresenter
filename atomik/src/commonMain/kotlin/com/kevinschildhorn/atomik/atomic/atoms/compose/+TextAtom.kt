// ktlint-disable filename
package com.kevinschildhorn.atomik.atomic.atoms

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.kevinschildhorn.atomik.atomic.atoms.interfaces.TextAtom
import com.kevinschildhorn.atomik.color.base.composeColor
import com.kevinschildhorn.atomik.typography.fontWeight

/**
 * Creates a compose [TextStyle] from the [TextAtom]
 *
 * Uses the default FontFamily from the atom
 * @return returns a compose [TextStyle]
 */
public val TextAtom.textStyle: TextStyle
    get() {
        return TextStyle(
            fontFamily = this.fontFamily?.fontFamily,
            fontWeight = this.typography.weight.fontWeight,
            fontSize = this.typography.size.sp,
            color = this.textColor.composeColor
        )
    }

/**
 * Creates a compose [TextStyle] from the [TextAtom]
 *
 * @param fontFamily the [FontFamily] that can be passed in from the platform code
 * @return returns a compose [TextStyle]
 */
public fun TextAtom.textStyle(fontFamily: FontFamily): TextStyle =
    TextStyle(
        fontFamily = fontFamily,
        fontWeight = this.typography.weight.fontWeight,
        fontSize = this.typography.size.sp,
        color = this.textColor.composeColor
    )
