package com.kevinschildhorn.fotopresenter.domain

import co.touchlab.kermit.Logger
import com.kevinschildhorn.fotopresenter.data.DirectoryContents
import com.kevinschildhorn.fotopresenter.data.Path
import com.kevinschildhorn.fotopresenter.data.repositories.DirectoryRepository

/**
Retrieving Directory Contents from Path TODO: REMOVE
 **/
class RetrieveDirectoryContentsUseCase(
    private val directoryRepository: DirectoryRepository,
    private val logger: Logger,
) {
    suspend operator fun invoke(
        path: Path,
        recursively: Boolean,
        tags: List<String> = emptyList(),
        inclusiveTags: Boolean = false,
    ): DirectoryContents {
        logger.d { "Getting directory Contents at path '$path'" }
        var directoryContents = if (recursively)
            directoryRepository.getDirectoryContentsRecursive(path)
        else
            directoryRepository.getDirectoryContents(path)

        directoryContents.copy()

        return directoryContents
    }
}
