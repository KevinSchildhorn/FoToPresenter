package com.kevinschildhorn.fotopresenter.ui.shared

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.loadImageBitmap
import com.hierynomus.smbj.share.File

actual fun getBitmapFromFile(file: File, size:Int): ImageBitmap? =
    file.inputStream.buffered().use(::loadImageBitmap)
