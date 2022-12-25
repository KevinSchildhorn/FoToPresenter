package me.kevinschildhorn.atomik.typography.base

import platform.UIKit.UIFont

actual class AtomikFontFamily(fonts: List<UIFont?>) {

    private var fontFamily: List<UIFont>

    init {
        fontFamily = fonts.filterNotNull()
    }
}