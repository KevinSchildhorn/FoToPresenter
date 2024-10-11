package com.kevinschildhorn.fotopresenter.ui

import androidx.compose.ui.graphics.ImageBitmap

actual class SharedImage {
    actual val byteArray: ByteArray = ByteArray()
    actual fun getImageBitmap(size:Int): ImageBitmap? = null
    actual fun close() {}
}
