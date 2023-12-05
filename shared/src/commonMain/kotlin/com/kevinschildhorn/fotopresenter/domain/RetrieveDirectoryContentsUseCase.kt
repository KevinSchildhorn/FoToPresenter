package com.kevinschildhorn.fotopresenter.domain

import com.kevinschildhorn.fotopresenter.data.DirectoryContents
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
        val directories = directoryRepository.getDirectoryContents(path)
        val images = directories.images.map {
            imageRepository.getImage(it)
        }
        return directories
    }
}