package com.kevinschildhorn.fotopresenter.domain

import com.kevinschildhorn.fotopresenter.data.DirectoryContents
import com.kevinschildhorn.fotopresenter.data.ImageDirectoryContent
import com.kevinschildhorn.fotopresenter.data.repositories.DirectoryRepository
import com.kevinschildhorn.fotopresenter.data.repositories.ImageRepository

/**
Retrieving Directories from Location
 **/
class RetrieveDirectoryContentsUseCase(
    private val directoryRepository: DirectoryRepository,
    private val imageRepository: ImageRepository,
) {
    suspend operator fun invoke(path:String): DirectoryContents {
        val directoryContents = directoryRepository.getDirectoryContents(path)
        val imageDirectories = directoryContents.images.map {
            ImageDirectoryContent(it.directory, image = imageRepository.getImage(it.directory))
        }
        return directoryContents.copy(images = imageDirectories)
    }
}