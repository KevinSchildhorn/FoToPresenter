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

        val fileMetadata = MetadataFileDetails(
            filePath = path,
            tags = tagList.toSet(),
        )

        metaData.files.removeIf { it.filePath == path }
        if (fileMetadata.tags.isNotEmpty()) metaData.files.add(fileMetadata)

        return dataSource.exportMetadata(metaData)
    }
}