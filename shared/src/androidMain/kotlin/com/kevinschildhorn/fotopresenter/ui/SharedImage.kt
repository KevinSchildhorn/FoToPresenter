package com.kevinschildhorn.fotopresenter.ui

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import com.hierynomus.smbj.share.File

actual fun getBitmapFromFile(file: File): ImageBitmap? {
    val options = BitmapFactory.Options()
    options.inSampleSize = 3
    BitmapFactory.decodeStream(file.inputStream, null, options)?.let {
        return Bitmap.createScaledBitmap(it, 200, 200, false).asImageBitmap()
    }

    return null
}
