package com.kevinschildhorn.fotopresenter.ui.shared

import com.mayakapps.kache.FileKache
import com.mayakapps.kache.KacheStrategy
import io.github.reactivecircus.cache4k.Cache
import java.io.File

interface CacheInterface {
    suspend fun getImage(id: String): SharedImage?

    suspend fun cacheImage(
        id: String,
        image: SharedImage,
    )
}

object SharedInMemoryCache : CacheInterface {
    private val imageCache = Cache.Builder<String, ByteArray>().build()

    override suspend fun getImage(id: String): SharedImage? = imageCache.get(id)?.let {
        SharedImage(it)
    }

    override suspend fun cacheImage(
        id: String,
        image: SharedImage,
    ) {
        imageCache.put(id, image.byteArray)
    }
}

class SharedFileCache(private val cacheLocation: String) : CacheInterface {

    override suspend fun getImage(id: String): SharedImage? {
        val cache = createCache()

        val test = cache?.get(id)
        println(test)
        val byteArray = cache?.get(id)?.toByteArray()
        return if (byteArray != null) SharedImage(byteArray) else null
    }

    override suspend fun cacheImage(
        id: String,
        image: SharedImage,
    ) {
        val cache = createCache()
        try {
            val imageData = cache?.put(id) { path ->
                val file = File(path)
                try {
                    val stream = file.outputStream()
                    stream.write(image.byteArray)
                    stream.close()
                    true
                } catch (e: Exception) {
                    println(e.localizedMessage)
                    false
                }
            }
            println(imageData)
        } finally {
            cache?.close()
        }
    }


    private suspend fun createCache(): FileKache? {
        try {
            println("Creating Cache")
            return FileKache(directory =  cacheLocation, maxSize = 10 * 1024 * 1024) {
                strategy = KacheStrategy.LRU

            }
        } catch (e: Exception) {
            println("Error Creating Cache")
            println(e.localizedMessage)
            return null
        }
    }
}

class MockSharedCache : CacheInterface {
    private val contents = mutableMapOf<String, ByteArray>()

    override suspend fun cacheImage(
        id: String,
        image: SharedImage,
    ) {
        contents[id] = image.byteArray
    }

    override suspend fun getImage(id: String): SharedImage? = contents[id]?.let {
        SharedImage(it)
    }
}
