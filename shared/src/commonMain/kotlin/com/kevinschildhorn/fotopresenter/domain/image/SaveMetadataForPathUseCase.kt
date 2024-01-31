package com.kevinschildhorn.fotopresenter.domain.image

import com.kevinschildhorn.fotopresenter.data.ImageSlideshowDetails
import com.kevinschildhorn.fotopresenter.data.MetadataFileDetails
import com.kevinschildhorn.fotopresenter.data.datasources.ImageMetadataDataSource
import org.koin.core.component.KoinComponent

class SaveMetadataForPathUseCase(
    private val dataSource: ImageMetadataDataSource,
) : KoinComponent {

    suspend operator fun invoke(
        path: String,
        tags: String,
    ): Boolean {
        val tagList: List<String> = tags.split(",").map { it.trim() }

        val metaData = dataSource.importMetaData()

        var fileMetadata: MetadataFileDetails =
            metaData.files.find { it.filePath == path } ?: MetadataFileDetails(
                filePath = path,
                tags = tagList.toSet(),
            )
        val newTags = fileMetadata.tags.toMutableSet()
        newTags.addAll(tagList.toSet())
        fileMetadata = fileMetadata.copy(tags = newTags)
        metaData.files.add(fileMetadata)

        return dataSource.exportMetadata(metaData)
    }
}