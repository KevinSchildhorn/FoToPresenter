@file:Suppress("ktlint:standard:filename")

package com.kevinschildhorn.fotopresenter.ui.shared

import coil3.Image

actual open class SharedImage actual constructor(actual val byteArray: ByteArray) {
    // TODO
    actual fun getCoilImage(size: Int): Image? = null
}
