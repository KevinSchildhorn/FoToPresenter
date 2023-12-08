package com.kevinschildhorn.fotopresenter.ui.shared

import androidx.compose.ui.graphics.ImageBitmap
import io.github.reactivecircus.cache4k.Cache

object SharedCache {
    private val imageCache = Cache.Builder<String, ImageBitmap>().build()

    fun getImage(id: String): ImageBitmap? = imageCache.get(id)

    fun cacheImage(
        id: String,
        imageBitmap: ImageBitmap,
    ) {
        imageCache.put(id, imageBitmap)
    }
}
