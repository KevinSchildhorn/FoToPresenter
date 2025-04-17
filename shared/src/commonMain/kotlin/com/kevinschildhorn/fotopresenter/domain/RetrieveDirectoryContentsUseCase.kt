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
    ): DirectoryContents {
        logger.d { "Getting directory Contents at path '$path'" }
        return if (recursively) {
            directoryRepository.getDirectoryContentsRecursive(path)
        } else {
            directoryRepository.getDirectoryContents(path)
        }
    }
}
