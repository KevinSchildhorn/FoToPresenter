// ktlint-disable filename
package com.kevinschildhorn.atomik.atomic.atoms.xml

import android.view.View
import android.widget.TextView
import com.kevinschildhorn.atomik.atomic.atoms.interfaces.ColorAtom
import com.kevinschildhorn.atomik.atomic.atoms.interfaces.TextAtom

/**
 * Applies a [ColorAtom] to a [TextView], setting the background color
 *
 * @param colorAtom the [ColorAtom] that will be applied to the [View]
 */
public fun View.applyColorAtom(colorAtom: ColorAtom?) {
    colorAtom?.let {
        setBackgroundColor(it.color.viewColor)
    }
}
