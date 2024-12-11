@file:Suppress("ktlint:standard:filename")

package com.kevinschildhorn.fotopresenter.ui.shared

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import co.touchlab.kermit.Logger
import coil3.asImage
import coil3.decode.DataSource
import coil3.fetch.FetchResult
import coil3.fetch.ImageFetchResult

actual open class SharedImage actual constructor(actual val byteArray: ByteArray) {
    actual fun getFetchResult(size: Int, logger: Logger?): FetchResult? {
        if(byteArray.isEmpty()){
            logger?.e { "Byte Array is Empty!" }
            return null
        }

        logger?.d { "Getting Android Bitmap from Byte Array" }
        val bitmap = getAndroidBitmap(byteArray, size, logger)
        if (bitmap == null) logger?.e { "Could not generate Bitmap" }
        logger?.v { "Getting Image from Bitmap" }
        val image = bitmap?.asImage()
        return if (image != null) {
            logger?.v { "Returning result" }
            ImageFetchResult(
                image = image,
                isSampled = true,
                dataSource = DataSource.NETWORK,
            )
        } else {
            logger?.e { "Image NOT Found" }
            null
        }
    }

    private fun getAndroidBitmap(
        byteArray: ByteArray,
        size: Int,
        logger: Logger?,
    ): Bitmap? {
        logger?.v { "Getting Android Bitmap" }
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size, options)

        logger?.v { "Got Options for bitmap: ${options.outWidth} x ${options.outHeight}" }
        val height: Int = options.outHeight
        val width: Int = options.outWidth
        val dimensions = getScaledDimensions(width, height, size)
        logger?.v { "Got scaled dimensions for bitmap: ${dimensions.first} x ${dimensions.second}" }

        val heightRatio: Int = Math.round(height.toFloat() / dimensions.second.toFloat())
        val widthRatio: Int = Math.round(width.toFloat() / dimensions.first.toFloat())
        val sampleSize = if (heightRatio < widthRatio) heightRatio else widthRatio
        logger?.v { "Setting Sample Size: $sampleSize" }
        options.inSampleSize = sampleSize

        options.inJustDecodeBounds = false
        logger?.v { "Decoding Byte Array (Size: ${byteArray.size})" }
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size, options)
    }
}
