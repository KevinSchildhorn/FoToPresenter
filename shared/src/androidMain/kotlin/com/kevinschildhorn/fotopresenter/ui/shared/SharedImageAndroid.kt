package com.kevinschildhorn.fotopresenter.ui.shared

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import co.touchlab.kermit.Logger
import com.hierynomus.smbj.share.File

actual fun getBitmapFromFile(file: File, size: Int): ImageBitmap? {

    val options = BitmapFactory.Options()
    options.inSampleSize = 2
    BitmapFactory.decodeStream(file.inputStream, null, options)?.let {
        val dimensions = getScaledDimensions(it.width, it.height, size)
        return Bitmap.createScaledBitmap(it, dimensions.first, dimensions.second, false)
            .asImageBitmap()
    }
    return null

    /*
    val logger = Logger.withTag("BitmapFromFile")

    logger.d { "Gathering details of bounds" }
    logger.v { "Input Stream is good: ${file.inputStream != null}" }
    var options = BitmapFactory.Options()
    options.inJustDecodeBounds = true
    BitmapFactory.decodeStream(file.inputStream, null, options)


    val height: Int = options.outHeight
    val width: Int = options.outWidth
    val dimensions = getScaledDimensions(width, height, size)
    logger.d { "getting scaled dimensions from $width and $height, is ${dimensions.first}:${dimensions.second}" }
    if (options.outWidth != -1 || options.outHeight != -1) {
        logger.d { "Got new options" }
        val heightRatio: Int = Math.round(height.toFloat() / dimensions.second.toFloat())
        val widthRatio: Int = Math.round(width.toFloat() / dimensions.first.toFloat())
        options.inSampleSize = if (heightRatio < widthRatio) heightRatio else widthRatio
        options.inJustDecodeBounds = false
    } else {
        logger.i { "Failed to decode stream with bounds" }
        options = BitmapFactory.Options()
        options.inSampleSize = 4
    }

    logger.d { "Decoding Stream with details" }
    val finalBitmap = BitmapFactory.decodeStream(file.inputStream, null, null)
    logger.d { "Final Bitmap retrieved, is not null: ${finalBitmap != null}" }
    return finalBitmap?.asImageBitmap()

     */
}