package com.kevinschildhorn.fotopresenter.ui.shared

import coil3.Image

expect class SharedImage(byteArray: ByteArray) {
    val byteArray: ByteArray

    fun getCoilImage(size: Int): Image?
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
