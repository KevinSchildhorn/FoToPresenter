package com.kevinschildhorn.fotopresenter.ui.shared

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asSkiaBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import org.jetbrains.skia.Image
import kotlin.jvm.Throws

actual object SharedImageConverter {
    actual fun convertBytes(byteArray: ByteArray): ImageBitmap {
        return Image.makeFromEncoded(byteArray).toComposeImageBitmap()
    }

    @Throws(Exception::class)
    actual fun convertImage(imageBitmap: ImageBitmap): ByteArray {
        return Image.makeFromBitmap(imageBitmap.asSkiaBitmap()).encodeToData()?.bytes!!
    }
}