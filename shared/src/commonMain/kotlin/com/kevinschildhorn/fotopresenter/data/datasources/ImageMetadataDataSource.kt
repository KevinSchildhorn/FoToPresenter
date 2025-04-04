package com.kevinschildhorn.fotopresenter.data.datasources

import co.touchlab.kermit.Logger
import com.ashampoo.kim.Kim
import com.ashampoo.kim.common.convertToPhotoMetadata
import com.ashampoo.kim.format.tiff.constants.ExifTag
import com.kevinschildhorn.fotopresenter.data.MetadataFileDetails
import com.kevinschildhorn.fotopresenter.data.Path
import com.kevinschildhorn.fotopresenter.data.network.NetworkHandler

class ImageMetadataDataSource(
    private val logger: Logger?,
    private val networkHandler: NetworkHandler,
) {
    suspend fun readMetadataFromFile(filePath: Path): MetadataFileDetails? {
        networkHandler.getSharedImage(filePath)?.let { sharedImage ->
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


    // TODO
    suspend fun writeMetadataToFile(metadata:String, filePath: Path): MetadataFileDetails? {
        networkHandler.getSharedImage(filePath)?.let { sharedImage ->
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
