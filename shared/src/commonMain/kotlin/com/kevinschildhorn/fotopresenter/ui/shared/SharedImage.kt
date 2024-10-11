package com.kevinschildhorn.fotopresenter.ui.shared

import androidx.compose.ui.graphics.ImageBitmap

expect class SharedImage {
    fun getImageBitmap(size: Int): ImageBitmap?
    val byteArray: ByteArray
}
