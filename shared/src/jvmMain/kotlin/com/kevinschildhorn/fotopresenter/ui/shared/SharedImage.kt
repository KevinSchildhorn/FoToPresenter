package com.kevinschildhorn.fotopresenter.ui.shared

import androidx.compose.ui.graphics.ImageBitmap
import com.hierynomus.smbj.share.File

actual open class SharedImage(val file: File) {
    actual fun getImageBitmap(size: Int): ImageBitmap? = getBitmapFromFile(file, size)
    actual val byteArray: ByteArray = file.inputStream.readAllBytes()
}

expect fun getBitmapFromFile(file: File, size: Int): ImageBitmap?

