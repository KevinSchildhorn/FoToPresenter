package me.kevinschildhorn.common.architecture.data.datasources

import me.kevinschildhorn.common.architecture.data.datasources.interfaces.ImageDataSource
import me.kevinschildhorn.common.architecture.data.datasources.interfaces.Images

/**
Fetches [Images] from the local cache
 **/
class LocalImagesDataSource : ImageDataSource {

    override fun fetchImages(): Images {
        return emptyList()
    }

    fun updateImages(images: Images) {
    }
}
