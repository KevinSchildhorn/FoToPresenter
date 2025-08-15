package com.kevinschildhorn.fotopresenter.data.repositories

import co.touchlab.kermit.Logger
import com.kevinschildhorn.fotopresenter.data.ImageDirectory
import com.kevinschildhorn.fotopresenter.data.MetadataFileDetails
import com.kevinschildhorn.fotopresenter.data.Path
import com.kevinschildhorn.fotopresenter.data.datasources.ImageMetadataDataSource
import com.kevinschildhorn.fotopresenter.data.datasources.ImageTagSQLDataSource

class MetadataRepository(
    private val imageDataSource: ImageMetadataDataSource,
    private val sqlDataSource: ImageTagSQLDataSource,
    private val logger: Logger?,
) {

    suspend fun storeMetadata(images: List<ImageDirectory>) {
        logger?.i { "Storing Metadata of ${images.count()} images" }
        var imagesUpdated = 0
        images.forEach { image ->
            val path = image.details.fullPath
            val metadata = imageDataSource.readMetadataFromFile(path)
            metadata?.tags?.let { tags ->
                val tagCount = sqlDataSource.imageTagCount(path.toString())
                if(tags.isEmpty()) logger?.v { "The image has no Tags" }
                if(tagCount == 0) logger?.v { "There are no stored tags" }
                if (tagCount != tags.count()) {
                    imagesUpdated++
                    logger?.d { "Tag misalignment, storing tags" }
                    sqlDataSource.addImageTags(path.toString(), tags.toList())
                }
            }
        }
        logger?.i { "Finished Storing Metadata! $imagesUpdated Items actually changed" }
    }

    suspend fun writeMetadataToFile(
        metadata: String,
        filePath: Path,
    ): Boolean = imageDataSource.writeMetadataToFile(metadata, filePath)

    suspend fun getMetaData(filePath: Path): MetadataFileDetails? {

        val sqlMetadata = sqlDataSource.getImageTags(filePath.toString())
        return  if(sqlMetadata.isEmpty()){
            imageDataSource.readMetadataFromFile(filePath)
        } else {
            MetadataFileDetails(
                filePath = filePath,
                tags = sqlMetadata.toSet()
            )
        }
    }

    suspend fun readMetadataFromFile(filePath: Path): MetadataFileDetails? =
        imageDataSource.readMetadataFromFile(filePath)
}