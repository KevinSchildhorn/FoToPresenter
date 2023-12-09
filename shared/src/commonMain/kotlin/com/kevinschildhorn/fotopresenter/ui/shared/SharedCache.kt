package com.kevinschildhorn.fotopresenter.ui.shared

import androidx.compose.ui.graphics.ImageBitmap
import io.github.reactivecircus.cache4k.Cache

interface CacheInterface {
    fun getImage(id: String): ImageBitmap?
    fun cacheImage(
        id: String,
        imageBitmap: ImageBitmap,
    )
}

object SharedCache : CacheInterface {
    private val imageCache = Cache.Builder<String, ImageBitmap>().build()

    override fun getImage(id: String): ImageBitmap? = imageCache.get(id)

    override fun cacheImage(
        id: String,
        imageBitmap: ImageBitmap,
    ) {
        imageCache.put(id, imageBitmap)
    }
}

class MockSharedCache : CacheInterface {
    val contents = mutableMapOf<String, ImageBitmap>()

    override fun cacheImage(id: String, imageBitmap: ImageBitmap) {
        contents[id] = imageBitmap
    }

    override fun getImage(id: String): ImageBitmap? =
        contents[id]

}
