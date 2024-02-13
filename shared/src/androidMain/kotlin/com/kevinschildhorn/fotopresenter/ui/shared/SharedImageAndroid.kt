package com.kevinschildhorn.fotopresenter.ui.shared

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import com.hierynomus.smbj.share.File

actual fun getBitmapFromFile(file: File, size: Int): ImageBitmap? {

    val options = BitmapFactory.Options()
    options.inJustDecodeBounds = true
    BitmapFactory.decodeStream(file.inputStream, null, options)

    val height: Int = options.outHeight
    val width: Int = options.outWidth
    val dimensions = getScaledDimensions(width, height, size)
    val heightRatio: Int = Math.round(height.toFloat() / dimensions.second.toFloat())
    val widthRatio: Int = Math.round(width.toFloat() / dimensions.first.toFloat())
    options.inSampleSize = if (heightRatio < widthRatio) heightRatio else widthRatio

    options.inJustDecodeBounds = false
    return BitmapFactory.decodeStream(file.inputStream, null, options)?.asImageBitmap()
}