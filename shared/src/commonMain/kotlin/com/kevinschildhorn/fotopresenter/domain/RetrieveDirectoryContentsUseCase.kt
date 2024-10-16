package com.kevinschildhorn.fotopresenter.domain

import co.touchlab.kermit.Logger
import com.kevinschildhorn.fotopresenter.data.DirectoryContents
import com.kevinschildhorn.fotopresenter.data.repositories.DirectoryRepository

/**
Retrieving Directory Contents from Path
 **/
class RetrieveDirectoryContentsUseCase(
    private val directoryRepository: DirectoryRepository,
    private val logger: Logger,
) {
    suspend operator fun invoke(path: String): DirectoryContents {
        logger.i { "Getting directory Contents at path $path" }
        return directoryRepository.getDirectoryContents(path)
    }
}
