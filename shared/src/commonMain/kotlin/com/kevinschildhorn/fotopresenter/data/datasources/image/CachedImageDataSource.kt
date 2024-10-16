package com.kevinschildhorn.fotopresenter.data.datasources.image

import app.cash.sqldelight.db.SqlDriver
import co.touchlab.kermit.Logger
import com.kevinschildhorn.fotopresenter.PlaylistDatabase
import com.kevinschildhorn.fotopresenter.data.network.NetworkDirectoryDetails
import com.kevinschildhorn.fotopresenter.ui.shared.CacheInterface
import com.kevinschildhorn.fotopresenter.ui.shared.SharedImage
import com.kevinschildhorn.fotopresenter.Image as SQLImage

class CachedImageDataSource(
    private val cache: CacheInterface,
    private val logger: Logger,
    driver: SqlDriver,
    private val logger: Logger,
) {
    private val database = PlaylistDatabase(driver)

    suspend fun getImage(directory: NetworkDirectoryDetails): SharedImage? {
        logger.i { "Getting Image from Cache ${directory.cacheId}" }
        return try {
            cache.getImage(directory.cacheId)
            //val image = database.imageQueries.selectImageByName(directory.cacheId).executeAsOne()
            //SharedImage(image.image)
        } catch (e: Exception) {
            logger.e(e) { "Image NOT found" }
            logger.e { e.localizedMessage ?: "" }
            null
        }
    }

    fun saveImage(
        directory: NetworkDirectoryDetails,
        image: SharedImage,
    ) {
        logger.i { "Saving Image To Cache ${directory.cacheId}" }
        cache.cacheImage(directory.cacheId, image)
        //database.imageQueries.insertImage(
        //    directory.cacheId,
        //    image.byteArray,
        //)
        logger.i { "Image Saved" }
        // cache.cacheImage(directory.cacheId, image) TODO
    }

    private val NetworkDirectoryDetails.cacheId: String
        get() = "$name.$id"
}
