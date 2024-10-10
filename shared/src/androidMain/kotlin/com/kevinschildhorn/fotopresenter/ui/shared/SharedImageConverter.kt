package com.kevinschildhorn.fotopresenter.ui.shared

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import java.io.ByteArrayOutputStream


actual object SharedImageConverter {
    actual fun convertBytes(byteArray: ByteArray): ImageBitmap {
        val bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
        return bmp.asImageBitmap()
    }

    actual fun convertImage(imageBitmap: ImageBitmap): ByteArray {
        val bitmap = imageBitmap.asAndroidBitmap()
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        val byteArray = stream.toByteArray()
        return byteArray
    }

    private fun Bitmap.convertToByteArray(): ByteArray {
        val stream = ByteArrayOutputStream()
        this.compress(Bitmap.CompressFormat.PNG, 100, stream)
        val byteArray = stream.toByteArray()
        if (!this.isRecycled) {
            this.recycle();
        }
        return byteArray
    }
}