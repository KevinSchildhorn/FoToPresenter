package com.kevinschildhorn.fotopresenter.ui

import androidx.compose.ui.graphics.ImageBitmap
import com.hierynomus.smbj.share.File

actual open class SharedImage(val file: File) {
    actual fun getImageBitmap(): ImageBitmap? = getBitmapFromFile(file)
}

expect fun getBitmapFromFile(file: File): ImageBitmap?
