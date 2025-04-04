package com.kevinschildhorn.fotopresenter.data.datasources.image

import co.touchlab.kermit.Logger
import com.kevinschildhorn.fotopresenter.data.network.NetworkDirectoryDetails
import com.kevinschildhorn.fotopresenter.ui.shared.CacheInterface
import com.kevinschildhorn.fotopresenter.ui.shared.SharedImage

class CachedImageDataSource(
    private val cache: CacheInterface,
    private val logger: Logger? = null,
    // driver: SqlDriver,
) {
    // private val database = PlaylistDatabase(driver)

    suspend fun getImage(directory: NetworkDirectoryDetails): SharedImage? {
        logger?.v { "Getting Image from Cache ${directory.cacheId}" }
        return try {
            cache.getImage(directory.cacheId)
            // val image = database.imageQueries.selectImageByName(directory.cacheId).executeAsOne()
            // SharedImage(image.image)
        } catch (e: Exception) {
            logger?.e(e) { "Image NOT found" }
            null
        }
    }

    suspend fun saveImage(
        directory: NetworkDirectoryDetails,
        image: SharedImage,
    ) {
        logger?.d { "Saving Image To Cache: ${directory.cacheId}" }
        cache.cacheImage(directory.cacheId, image)
        // database.imageQueries.insertImage(
        //    directory.cacheId,
        //    image.byteArray,
        // )
        logger?.d { "Image Saved: ${directory.cacheId}" }
        // cache.cacheImage(directory.cacheId, image) TODO
    }

    private val NetworkDirectoryDetails.cacheId: String
        get() = "$name.$id"
}
