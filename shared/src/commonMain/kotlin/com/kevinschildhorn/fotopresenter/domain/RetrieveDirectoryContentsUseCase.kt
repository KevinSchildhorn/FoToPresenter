package com.kevinschildhorn.fotopresenter.domain

import com.kevinschildhorn.fotopresenter.data.DirectoryContents
import com.kevinschildhorn.fotopresenter.data.ImageDirectoryContent
import com.kevinschildhorn.fotopresenter.data.network.NetworkDirectory
import com.kevinschildhorn.fotopresenter.data.repositories.DirectoryRepository
import com.kevinschildhorn.fotopresenter.data.repositories.ImageRepository
import com.kevinschildhorn.fotopresenter.ui.SharedImage
import co.touchlab.kermit.Logger

/**
Retrieving Directories from Location
 **/
class RetrieveDirectoryContentsUseCase(
    private val directoryRepository: DirectoryRepository,
    private val imageRepository: ImageRepository,
    private val logger: Logger,
) {
    suspend operator fun invoke(path: String): DirectoryContents {
        val directoryContents = directoryRepository.getDirectoryContents(path)
        return directoryContents.updateImages {
            imageRepository.getImage(it)
        }
    }
}

private suspend fun DirectoryContents.updateImages(block: suspend (NetworkDirectory) -> SharedImage?): DirectoryContents =
    this.copy(
        images = images.map {
            ImageDirectoryContent(it.directory, image = block(it.directory))
        }
    )