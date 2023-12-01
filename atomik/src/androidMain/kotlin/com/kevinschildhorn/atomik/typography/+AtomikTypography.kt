// ktlint-disable filename
package com.kevinschildhorn.atomik.typography

import android.graphics.Typeface
import com.kevinschildhorn.atomik.typography.base.AtomikTypography
import com.kevinschildhorn.atomik.typography.base.AtomikTypographyWeight

/*
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.kevinschildhorn.atomik.typography.base.AtomikTypography

fun AtomikTypography.asComposeTextStyle(fontFamily: FontFamily): TextStyle =
    TextStyle(
        fontFamily = fontFamily,
        fontWeight = this.weight.fontWeight,
        fontSize = this.size.sp,
    )
*/

/*
 * Converting the Weight to a Typeface
 */
public val AtomikTypography.typeFace: Typeface
    get() = when (this.weight) {
        AtomikTypographyWeight.BOLD -> Typeface.DEFAULT_BOLD
        else -> Typeface.DEFAULT
    }
