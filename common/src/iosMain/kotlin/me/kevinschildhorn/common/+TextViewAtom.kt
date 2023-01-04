package me.kevinschildhorn.common

import me.kevinschildhorn.atomik.atomic.atoms.TextViewAtom
import me.kevinschildhorn.atomik.atomic.atoms.interfaces.TextAtom
import me.kevinschildhorn.atomik.atomic.atoms.uiFont
import platform.UIKit.UIFont

val TextViewAtom.uiFont: UIFont?
    get() = (this as TextAtom).uiFont
