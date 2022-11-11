package me.kevinschildhorn.common.layers.data.datasources

import me.kevinschildhorn.common.layers.data.datasources.interfaces.ImageDataSource
import me.kevinschildhorn.common.layers.data.datasources.interfaces.Images

/**
Fetches [Images] from the FTP server
 **/
class RemoteImagesDataSource : ImageDataSource {

    override fun fetchImages(): Images {
        return emptyList()
    }
}
