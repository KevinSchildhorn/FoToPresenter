package com.kevinschildhorn.fotopresenter.data.datasources

import app.cash.sqldelight.db.SqlDriver
import co.touchlab.kermit.Logger
import com.kevinschildhorn.fotopresenter.PlaylistDatabase
import org.koin.core.component.KoinComponent

class ImageTagSQLDataSource(
    driver: SqlDriver,
    private val logger: Logger? = null,
) : KoinComponent {
    private val database = PlaylistDatabase(driver)

    fun imageTagCount(directoryPath: String): Int = try {
        database.imageTagsQueries.selectImageTagCount(directoryPath).executeAsOneOrNull()?.toInt() ?: 0
    } catch (e: Exception){
        0
    }

    fun addImageTags(
        directoryPath: String,
        tags: List<String>,
    ) {
        try {
            logger?.i { "Adding tags for $directoryPath" }
            val count = database.imageTagsQueries.selectImageTagCount(directoryPath).executeAsOneOrNull() ?: -1
            if (count.toInt() == tags.count()) return

            database.imageTagsQueries.deleteImageTags(directoryPath)
            tags.forEach { tag ->
                database.imageTagsQueries.insertImageTag(directoryPath, tag)
            }
        } catch (e: Exception) {
            tags.forEach { tag ->
                database.imageTagsQueries.insertImageTag(directoryPath, tag)
            }
        }
    }

    fun getImageTags(directoryPath: String): List<String> =
        database.imageTagsQueries.selectImageTags(directoryPath).executeAsList().map { it.tag }
}
