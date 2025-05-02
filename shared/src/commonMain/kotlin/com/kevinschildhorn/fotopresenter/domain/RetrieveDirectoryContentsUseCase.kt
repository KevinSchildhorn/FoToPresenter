package com.kevinschildhorn.fotopresenter.domain

import co.touchlab.kermit.Logger
import com.kevinschildhorn.fotopresenter.data.DirectoryContents
import com.kevinschildhorn.fotopresenter.data.Path
import com.kevinschildhorn.fotopresenter.data.datasources.ImageMetadataDataSource
import com.kevinschildhorn.fotopresenter.data.repositories.DirectoryRepository

/**
Retrieving Directory Contents from Path TODO: REMOVE
 **/
class RetrieveDirectoryContentsUseCase(
    private val directoryRepository: DirectoryRepository,
    private val imageMetadataDataSource: ImageMetadataDataSource,
    private val logger: Logger,
) {
    suspend operator fun invoke(
        path: Path,
        recursively: Boolean,
        tags: List<String> = emptyList(),
        inclusiveTags: Boolean = false,
    ): DirectoryContents {
        logger.d { "Getting directory Contents at path '$path'" }
        val directoryContents = if (recursively)
            directoryRepository.getDirectoryContentsRecursive(path)
        else
            directoryRepository.getDirectoryContents(path)

        return if (tags.isNotEmpty()) {
            val newImages = directoryContents.images.map {
                val metaData = imageMetadataDataSource.readMetadataFromFile(it.details.fullPath)
                it.copy(metaData = metaData)
            }
            directoryContents.copy(images = newImages).filteredByTags(tags, inclusiveTags)
        }
        else directoryContents
    }
}
