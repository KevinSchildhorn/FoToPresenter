package com.kevinschildhorn.fotopresenter.ui.shared

import androidx.compose.ui.graphics.ImageBitmap

expect class SharedImage {
    val byteArray: ByteArray
    fun getImageBitmap(size: Int): ImageBitmap?
    fun close()
}
