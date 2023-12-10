package com.kevinschildhorn.fotopresenter.data.datasources

import androidx.compose.ui.graphics.ImageBitmap
import com.kevinschildhorn.fotopresenter.data.network.NetworkDirectoryDetails
import com.kevinschildhorn.fotopresenter.ui.shared.CacheInterface

class ImageCacheDataSource(private val cache: CacheInterface) {
    fun getImage(directory: NetworkDirectoryDetails): ImageBitmap? = cache.getImage(directory.cacheId)

    fun saveImage(
        directory: NetworkDirectoryDetails,
        bitmap: ImageBitmap,
    ) {
        cache.cacheImage(directory.cacheId, bitmap)
    }

    private val NetworkDirectoryDetails.cacheId: String
        get() = "$name.$id"
}
