package com.kevinschildhorn.fotopresenter.ui.shared

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
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
}
