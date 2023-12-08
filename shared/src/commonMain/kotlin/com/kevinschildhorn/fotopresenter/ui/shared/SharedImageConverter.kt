package com.kevinschildhorn.fotopresenter.ui.shared

import androidx.compose.ui.graphics.ImageBitmap

expect object SharedImageConverter {
    fun convertBytes(byteArray: ByteArray): ImageBitmap

    fun convertImage(imageBitmap: ImageBitmap): ByteArray
}

fun getScaledDimensions(
    width: Int,
    height: Int,
    minSize: Int,
): Pair<Int, Int> {
    val newWidth: Float
    val newHeight: Float
    if (height < width) {
        newHeight = minSize.toFloat()
        val ratio: Float = (newHeight / height)
        newWidth = width * ratio
    } else {
        newWidth = minSize.toFloat()
        val ratio: Float = (newWidth / width)
        newHeight = height * ratio
    }
    return Pair(newWidth.toInt(), newHeight.toInt())
}
