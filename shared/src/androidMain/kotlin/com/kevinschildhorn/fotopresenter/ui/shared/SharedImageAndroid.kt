@file:Suppress("ktlint:standard:filename")

package com.kevinschildhorn.fotopresenter.ui.shared

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import co.touchlab.kermit.Logger
import coil3.asImage
import coil3.decode.DataSource
import coil3.fetch.FetchResult
import coil3.fetch.ImageFetchResult
import com.ashampoo.kim.Kim
import com.ashampoo.kim.common.convertToPhotoMetadata
import com.ashampoo.kim.model.TiffOrientation

actual open class SharedImage actual constructor(actual val byteArray: ByteArray) {
    actual fun getFetchResult(size: Int, logger: Logger?): FetchResult? {
        if (byteArray.isEmpty()) {
            logger?.e { "Byte Array is Empty!" }
            return null
        }

        logger?.d { "Getting Android Bitmap from Byte Array" }
        val bitmap = getAndroidBitmap(byteArray, size, logger)
        logger?.v { "Getting Image from Bitmap" }
        val image = bitmap.asImage()
        logger?.v { "Returning result" }
        return ImageFetchResult(
            image = image,
            isSampled = true,
            dataSource = DataSource.NETWORK,
        )
    }

    private fun getAndroidBitmap(
        byteArray: ByteArray,
        size: Int,
        logger: Logger?,
    ): Bitmap {
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
        val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size, options)

        val imageRotation = getImageRotationOffset(byteArray) ?: return bitmap
        val matrix = Matrix()
        matrix.postRotate(imageRotation)
        val rotatedBitmap = Bitmap.createBitmap(
            bitmap,
            0,
            0,
            bitmap.width,
            bitmap.height,
            matrix,
            true
        )
        return rotatedBitmap
    }

    private fun getImageRotationOffset(byteArray: ByteArray): Float? {
        val photoMetadata = Kim.readMetadata(byteArray)?.convertToPhotoMetadata()
        if (photoMetadata?.orientation != null) {
            val orientation = photoMetadata.orientation!!
            return when (orientation) {
                TiffOrientation.ROTATE_LEFT -> -90f
                TiffOrientation.ROTATE_RIGHT -> 90f
                TiffOrientation.UPSIDE_DOWN -> 180f
                else -> null
            }
        }
        return null
    }
}
