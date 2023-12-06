package com.kevinschildhorn.fotopresenter.ui

import androidx.compose.ui.graphics.ImageBitmap
import com.hierynomus.smbj.share.File

class AndroidSharedImage(file:File): SharedImage(file) {

    override fun getBitmap(): ImageBitmap? {
        return super.getBitmap()
    }
}