package com.kevinschildhorn.fotopresenter.data.datasources.image

import androidx.collection.size
import app.cash.sqldelight.db.SqlDriver
import co.touchlab.kermit.Logger
import com.kevinschildhorn.fotopresenter.Image as SQLImage
import com.kevinschildhorn.fotopresenter.PlaylistDatabase
import com.kevinschildhorn.fotopresenter.data.network.NetworkDirectoryDetails
import com.kevinschildhorn.fotopresenter.ui.shared.CacheInterface
import com.kevinschildhorn.fotopresenter.ui.shared.SharedImage
import java.io.InputStream

class CachedImageDataSource(
    private val cache: CacheInterface,
    driver: SqlDriver,
    private val logger: Logger
) {
    private val database = PlaylistDatabase(driver)

    fun getImage(directory: NetworkDirectoryDetails): SharedImage? {
        logger.i { "Getting Image from Cache ${directory.cacheId}" }
        return try {
            val image: SQLImage = database.imageQueries.selectImageByName(directory.cacheId).executeAsOne()
            SharedImage(image.image)
        } catch (e: Exception) {
            logger.e(e) { "Image NOT found" }
            null
        }
    }

    fun saveImage(directory: NetworkDirectoryDetails, image: SharedImage) {
        logger.i { "Saving Image To Cache ${directory.cacheId}" }
        database.imageQueries.insertImage(
            directory.cacheId,
            image.byteArray,
        )
        logger.i { "Image Saved" }
        //cache.cacheImage(directory.cacheId, image) TODO
    }

    private val NetworkDirectoryDetails.cacheId: String
        get() = "$name.$id"


    fun readBlobInChunks(cursor: Cursor, columnIndex: Int): InputStream {
        return object : InputStream() {
            private var pos = 0
            private val blob = cursor.getBlob(columnIndex)

            override fun read(): Int {
                if (pos >= blob.size) {
                    return -1
                }
                return blob[pos++].toInt() and 0xFF
            }

            // Implement other methods of InputStream if needed
        }
    }
}
