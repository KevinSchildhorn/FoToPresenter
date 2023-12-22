package com.kevinschildhorn.fotopresenter.data.datasources

import androidx.compose.ui.graphics.ImageBitmap
import app.cash.sqldelight.db.SqlDriver
import co.touchlab.kermit.Logger
import com.kevinschildhorn.fotopresenter.PlaylistDatabase
import com.kevinschildhorn.fotopresenter.data.network.NetworkDirectoryDetails
import com.kevinschildhorn.fotopresenter.ui.shared.CacheInterface
import com.kevinschildhorn.fotopresenter.ui.shared.SharedImageConverter

class ImageCacheDataSource(
    private val cache: CacheInterface,
    driver: SqlDriver,
    private val logger: Logger
) {
    private val database = PlaylistDatabase(driver)


    fun getImage(directory: NetworkDirectoryDetails): ImageBitmap? {
        logger.i { "Getting Image from Cache ${directory.cacheId}" }
        return try {
            val image = database.imageQueries.selectImageByName(directory.cacheId).executeAsOne()
            SharedImageConverter.convertBytes(image.image)
        } catch (e: Exception) {
            logger.e(e) { "Image NOT found" }

            null
        }
        //return cache.getImage(directory.cacheId)
    }

    fun saveImage(
        directory: NetworkDirectoryDetails,
        bitmap: ImageBitmap,
    ) {
        logger.i { "Saving Image To Cache ${directory.cacheId}" }
        database.imageQueries.insertImage(
            directory.cacheId,
            SharedImageConverter.convertImage(bitmap)
        )
        logger.i { "Image Saved" }

        //DiscCache.storeFile(directory.cacheId, bitmap)
    }

    private val NetworkDirectoryDetails.cacheId: String
        get() = "$name.$id"
}
