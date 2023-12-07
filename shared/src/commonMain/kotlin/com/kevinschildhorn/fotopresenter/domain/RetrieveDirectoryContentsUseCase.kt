package com.kevinschildhorn.fotopresenter.domain

import co.touchlab.kermit.Logger
import com.kevinschildhorn.fotopresenter.data.DirectoryContents
import com.kevinschildhorn.fotopresenter.data.ImageDirectory
import com.kevinschildhorn.fotopresenter.data.network.NetworkDirectoryDetails
import com.kevinschildhorn.fotopresenter.data.repositories.DirectoryRepository
import com.kevinschildhorn.fotopresenter.data.repositories.ImageRepository
import com.kevinschildhorn.fotopresenter.ui.SharedImage

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

private suspend fun DirectoryContents.updateImages(block: suspend (NetworkDirectoryDetails) -> SharedImage?): DirectoryContents =
    this.copy(
        images =
            images.map {
                ImageDirectory(it.details, image = block(it.details))
            },
    )
