package me.kevinschildhorn.common.layers.data.datasources

import me.kevinschildhorn.common.layers.data.datasources.interfaces.ImageDataSource
import me.kevinschildhorn.common.layers.data.datasources.interfaces.Images

/**
Fetches [Images] from the local cache
 **/
class LocalImagesDataSource : ImageDataSource {

    override fun fetchImages(): Images {
        return emptyList()
    }

    fun updateImages(images: Images){

    }
}