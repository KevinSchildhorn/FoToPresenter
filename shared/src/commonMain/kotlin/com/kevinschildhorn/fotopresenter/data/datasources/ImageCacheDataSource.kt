package com.kevinschildhorn.fotopresenter.data.datasources

import androidx.compose.ui.graphics.ImageBitmap
import com.kevinschildhorn.fotopresenter.data.network.NetworkDirectoryDetails
import com.kevinschildhorn.fotopresenter.ui.shared.SharedCache

class ImageCacheDataSource {
    fun getImage(directory: NetworkDirectoryDetails): ImageBitmap? = SharedCache.getImage(directory.cacheId)

    fun saveImage(
        directory: NetworkDirectoryDetails,
        bitmap: ImageBitmap,
    ) {
        SharedCache.cacheImage(directory.cacheId, bitmap)
    }

    private val NetworkDirectoryDetails.cacheId: String
        get() = "$name.$id"
}
