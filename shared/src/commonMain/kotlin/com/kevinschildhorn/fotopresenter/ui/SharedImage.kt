package com.kevinschildhorn.fotopresenter.ui

import androidx.compose.ui.graphics.ImageBitmap

expect class SharedImage {
    fun getBitmap(): ImageBitmap?
}