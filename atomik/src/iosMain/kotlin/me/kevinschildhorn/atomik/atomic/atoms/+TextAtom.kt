package me.kevinschildhorn.atomik.atomic.atoms

import me.kevinschildhorn.atomik.atomic.atoms.interfaces.TextAtom
import platform.UIKit.UIFont

fun TextAtom.formattedFont(font: UIFont): UIFont {
    val newFont = font.fontWithSize(this.typography.size.toDouble())
    return newFont
}