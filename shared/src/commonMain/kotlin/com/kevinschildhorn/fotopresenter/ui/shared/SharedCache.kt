package com.kevinschildhorn.fotopresenter.ui.shared

import io.github.reactivecircus.cache4k.Cache

interface CacheInterface {
    fun getImage(id: String): SharedImage?

    fun cacheImage(
        id: String,
        image: SharedImage,
    )
}

object SharedInMemoryCache : CacheInterface {
    private val imageCache = Cache.Builder<String, ByteArray>().build()

    override fun getImage(id: String): SharedImage? = imageCache.get(id)?.let {
        SharedImage(it)
    }

    override fun cacheImage(
        id: String,
        image: SharedImage,
    ) {
        imageCache.put(id, image.byteArray)
    }
}

class MockSharedCache : CacheInterface {
    private val contents = mutableMapOf<String, ByteArray>()

    override fun cacheImage(
        id: String,
        image: SharedImage,
    ) {
        contents[id] = image.byteArray
    }

    override fun getImage(id: String): SharedImage? = contents[id]?.let {
        SharedImage(it)
    }
}
