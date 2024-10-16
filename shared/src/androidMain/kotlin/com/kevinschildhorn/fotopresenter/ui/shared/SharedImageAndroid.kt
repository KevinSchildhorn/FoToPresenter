@file:Suppress("ktlint:standard:filename")

package com.kevinschildhorn.fotopresenter.ui.shared

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import coil3.Image
import coil3.asImage

actual open class SharedImage actual constructor(actual val byteArray: ByteArray) {
    actual fun getCoilImage(size: Int): Image? = getAndroidBitmap(byteArray, size)?.asImage()

    private fun getAndroidBitmap(
        byteArray: ByteArray,
        size: Int,
    ): Bitmap? {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size, options)

        val height: Int = options.outHeight
        val width: Int = options.outWidth
        val dimensions = getScaledDimensions(width, height, size)
        val heightRatio: Int = Math.round(height.toFloat() / dimensions.second.toFloat())
        val widthRatio: Int = Math.round(width.toFloat() / dimensions.first.toFloat())
        options.inSampleSize = if (heightRatio < widthRatio) heightRatio else widthRatio

        options.inJustDecodeBounds = false
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size, options)
    }
}
