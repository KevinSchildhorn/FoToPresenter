package com.kevinschildhorn.fotopresenter.domain

import co.touchlab.kermit.Logger
import com.kevinschildhorn.fotopresenter.data.DirectoryContents
import com.kevinschildhorn.fotopresenter.data.Path
import com.kevinschildhorn.fotopresenter.data.datasources.ImageMetadataDataSource
import com.kevinschildhorn.fotopresenter.data.repositories.DirectoryRepository
import kotlinx.datetime.LocalDate

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
        startDate: LocalDate? = null,
        endDate: LocalDate? = null,
    ): DirectoryContents {
        logger.d { "Getting directory Contents at path '$path'" }
        var directoryContents =
            if (recursively) {
                directoryRepository.getDirectoryContentsRecursive(path)
            } else {
                directoryRepository.getDirectoryContents(path)
            }

        if (tags.isNotEmpty()) {
            val newImages =
                directoryContents.images.map {
                    val metaData = imageMetadataDataSource.readMetadataFromFile(it.details.fullPath)
                    it.copy(metaData = metaData)
                }
            directoryContents =
                directoryContents
                    .copy(images = newImages)
                    .filteredByTags(tags, inclusiveTags)
        }
        if (startDate != null && endDate != null) {
            directoryContents = directoryContents.filteredByDate(startDate, endDate)
        }

        return directoryContents
    }
}
