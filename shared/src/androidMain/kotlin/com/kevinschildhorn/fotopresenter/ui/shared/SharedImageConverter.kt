package com.kevinschildhorn.fotopresenter.ui.shared

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import java.nio.ByteBuffer

actual object SharedImageConverter {
    actual fun convertBytes(byteArray: ByteArray): ImageBitmap {
        val bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
        return bmp.asImageBitmap()
    }

    actual fun convertImage(imageBitmap: ImageBitmap): ByteArray {
        return imageBitmap.asAndroidBitmap().convertToByteArray()
    }

    private fun Bitmap.convertToByteArray(): ByteArray {
        val size = this.byteCount
        val buffer = ByteBuffer.allocate(size)
        val bytes = ByteArray(size)
        this.copyPixelsToBuffer(buffer)
        buffer.rewind()
        buffer.get(bytes)
        return bytes
    }
}