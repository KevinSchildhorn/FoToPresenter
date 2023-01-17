package me.kevinschildhorn.atomik.atomic.atoms

import me.kevinschildhorn.atomik.atomic.atoms.interfaces.TextAtom
import platform.UIKit.UIFont

val TextAtom.uiFont: UIFont?
    get() = fontFamily?.uiFonts?.get(typography.weight)
        ?.fontWithSize(typography.size.toDouble())