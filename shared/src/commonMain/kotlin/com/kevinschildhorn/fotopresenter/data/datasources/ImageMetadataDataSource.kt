package com.kevinschildhorn.fotopresenter.data.datasources

import co.touchlab.kermit.Logger
import com.ashampoo.kim.Kim
import com.ashampoo.kim.common.convertToPhotoMetadata
import com.ashampoo.kim.model.MetadataUpdate
import com.kevinschildhorn.fotopresenter.data.MetadataFileDetails
import com.kevinschildhorn.fotopresenter.data.Path
import com.kevinschildhorn.fotopresenter.data.network.NetworkHandler
import com.kevinschildhorn.fotopresenter.ui.shared.SharedImage

interface ImageMetadataDataSource {
    suspend fun readMetadataFromFile(filePath: Path): MetadataFileDetails?

    suspend fun writeMetadataToFile(
        metadata: String,
        filePath: Path,
    ): Boolean
}

class NetworkImageMetadataDataSource(
    private val logger: Logger?,
    private val networkHandler: NetworkHandler,
) : ImageMetadataDataSource {
    override suspend fun readMetadataFromFile(filePath: Path): MetadataFileDetails? {
        logger?.i { "readMetadataFromFile" }
        networkHandler.getSharedImage(filePath)?.let { sharedImage ->
            logger?.i { "Reading MetaData" }
            val metadata = Kim.readMetadata(sharedImage.byteArray)
            logger?.v { metadata.toString() }

            val photoMetadata = metadata?.convertToPhotoMetadata()
            val keywords = photoMetadata?.keywords ?: emptySet()
            logger?.v { keywords.toString() }

            return MetadataFileDetails(
                filePath = filePath,
                tags = keywords.toSet(),
            )
        }
        return null
    }

    override suspend fun writeMetadataToFile(
        metadata: String,
        filePath: Path,
    ): Boolean {
        logger?.i { "writeMetadataToFile" }
        logger?.v { "Getting Shared Image" }
        networkHandler.getSharedImage(filePath)?.let { sharedImage ->
            try {
                val set = metadata.split(",").toSet()
                logger?.i { "Metadata: $set" }
                val newByteArray = Kim.update(sharedImage.byteArray, MetadataUpdate.Keywords(set))
                logger?.i { "Metadata Updated" }

                networkHandler.setSharedImage(filePath, SharedImage(byteArray = newByteArray))
                logger?.i { "Shared Image Set" }
                return true
            } catch (e: Exception) {
                logger?.e(e) { "Error Updating Metadata" }
                return false
            }
        }
        return false
    }
}
