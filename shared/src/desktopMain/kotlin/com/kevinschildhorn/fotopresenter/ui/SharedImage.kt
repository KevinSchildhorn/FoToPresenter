package com.kevinschildhorn.fotopresenter.ui

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.loadImageBitmap
import com.hierynomus.smbj.share.File

actual fun getBitmapFromFile(file: File): ImageBitmap? =
    file.inputStream.buffered().use(::loadImageBitmap)
