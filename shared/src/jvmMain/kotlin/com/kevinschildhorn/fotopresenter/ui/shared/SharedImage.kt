package com.kevinschildhorn.fotopresenter.ui.shared

import androidx.compose.ui.graphics.ImageBitmap
import com.hierynomus.smbj.share.File

actual open class SharedImage(val file: File) {
    actual fun getImageBitmap(size: Int): ImageBitmap? = getBitmapFromFile(file, size)
    actual val byteArray: ByteArray
        get() = file.inputStream.readAllBytes()
        /*
        get() {
            val bytes = ByteArray(file.inputStream.available())
            file.inputStream.read(bytes)
            return bytes
        }*/
}

expect fun getBitmapFromFile(file: File, size: Int): ImageBitmap?

