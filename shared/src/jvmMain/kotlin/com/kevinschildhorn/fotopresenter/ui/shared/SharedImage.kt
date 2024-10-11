package com.kevinschildhorn.fotopresenter.ui.shared

import androidx.compose.ui.graphics.ImageBitmap
import com.hierynomus.smbj.share.File

actual open class SharedImage(val file: File) {
    actual val byteArray: ByteArray = file.inputStream.readAllBytes()
    actual fun getImageBitmap(size: Int): ImageBitmap? = getBitmapFromFile(file, size)
    actual fun close(){
        file.close()
    }
}

expect fun getBitmapFromFile(file: File, size: Int): ImageBitmap?

