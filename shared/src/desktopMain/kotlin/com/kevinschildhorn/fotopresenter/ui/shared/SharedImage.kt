package com.kevinschildhorn.fotopresenter.ui.shared

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.loadImageBitmap
import com.hierynomus.smbj.share.File
import java.io.InputStream

actual fun getBitmapFromFile(inputStream: InputStream, size:Int): ImageBitmap? =
    inputStream.buffered().use(::loadImageBitmap)

