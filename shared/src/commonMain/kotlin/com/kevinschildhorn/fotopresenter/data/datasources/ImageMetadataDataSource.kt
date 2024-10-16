package com.kevinschildhorn.fotopresenter.data.datasources

import co.touchlab.kermit.Logger
import com.ashampoo.kim.Kim
import com.ashampoo.kim.common.convertToPhotoMetadata
import com.ashampoo.kim.format.tiff.constants.ExifTag
import com.kevinschildhorn.fotopresenter.data.MetadataDetails
import com.kevinschildhorn.fotopresenter.data.MetadataFileDetails
import com.kevinschildhorn.fotopresenter.data.network.NetworkHandler
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class ImageMetadataDataSource(
    private val logger: Logger?,
    private val networkHandler: NetworkHandler,
) {
    suspend fun importMetaData(): MetadataDetails {
        logger?.i { "Importing Metadata" }
        networkHandler.getMetadata()?.let {
            logger?.i { "Found Metadata" }
            return Json.decodeFromString<MetadataDetails>(it)
        }

        logger?.i { "No Metadata Found" }
        return MetadataDetails(mutableListOf())
    }

    suspend fun exportMetadata(metadata: MetadataDetails): Boolean {
        logger?.i { "Exporting Metadata" }
        try {
            val jsonString = Json.encodeToString(metadata)
            networkHandler.setMetadata(jsonString)
        } catch (e: Exception) {
            logger?.e(e) { "Error Exporting Metadata" }
            return false
        }

        logger?.i { "Successfully Exported Metadata" }
        return true
    }

    suspend fun readMetadataFromFile(filePath: String): MetadataFileDetails? {
        networkHandler.openImage(filePath)?.let { sharedImage ->
            val metadata = Kim.readMetadata(sharedImage.byteArray)
            println(metadata)

            val comments = metadata?.findStringValue(ExifTag.EXIF_TAG_USER_COMMENT)
            val keywords = comments?.split(",") ?: emptyList()

            val takenDate = metadata?.findStringValue(ExifTag.EXIF_TAG_DATE_TIME_ORIGINAL)
            println("Taken date: $takenDate")

            val photoMetadata = metadata?.convertToPhotoMetadata()
            photoMetadata?.orientation

            return MetadataFileDetails(
                filePath = filePath,
                tags = keywords.toSet(),
            )
        }
        return null
    }
}
