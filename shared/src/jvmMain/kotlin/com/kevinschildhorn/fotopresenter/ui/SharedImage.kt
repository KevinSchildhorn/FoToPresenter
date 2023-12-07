package com.kevinschildhorn.fotopresenter.ui

import androidx.compose.ui.graphics.ImageBitmap
import com.hierynomus.smbj.share.File

actual open class SharedImage(val file: File) {
    actual fun getImageBitmap(size: Int): ImageBitmap? = getBitmapFromFile(file, size)
}

expect fun getBitmapFromFile(file: File, size: Int): ImageBitmap?

