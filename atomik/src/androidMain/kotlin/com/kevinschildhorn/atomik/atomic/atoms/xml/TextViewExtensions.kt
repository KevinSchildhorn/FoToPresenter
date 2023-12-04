
@file:Suppress("ktlint:standard:filename")

package com.kevinschildhorn.atomik.atomic.atoms.xml

import android.widget.TextView
import com.kevinschildhorn.atomik.atomic.atoms.interfaces.ColorAtom
import com.kevinschildhorn.atomik.atomic.atoms.interfaces.TextAtom
import com.kevinschildhorn.atomik.typography.typeFace

/**
 * Applies a [TextAtom] to a [TextView], setting the text color and size
 *
 * @param textAtom the [TextAtom] that will be applied to the [TextView]
 */
public fun TextView.applyTextAtom(textAtom: TextAtom?) {
    textAtom?.let {
        setTextColor(it.textColor.viewColor)
        typeface = it.typography.typeFace
        textSize = it.typography.size.toFloat()
    }
}

/**
 * Applies a [ColorAtom] to a [TextView], setting the text color
 *
 * @param colorAtom the [ColorAtom] that will be applied to the [TextView]
 * @param foreground a [Boolean] indicating whether the atom should be applied to the foreground or background [TextView]
 */
public fun TextView.applyColorAtom(colorAtom: ColorAtom?, foreground: Boolean = true) {
    colorAtom?.let {
        if (foreground) {
            setTextColor(it.color.viewColor)
        } else {
            setBackgroundColor(it.color.viewColor)
        }
    }
}
