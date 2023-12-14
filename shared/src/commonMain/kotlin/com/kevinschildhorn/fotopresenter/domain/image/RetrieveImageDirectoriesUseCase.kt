package com.kevinschildhorn.fotopresenter.domain.image

import co.touchlab.kermit.Logger
import com.kevinschildhorn.fotopresenter.data.ImageDirectory
import com.kevinschildhorn.fotopresenter.data.network.NetworkDirectoryDetails
import com.kevinschildhorn.fotopresenter.domain.RetrieveDirectoryContentsUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/**
Retrieving Image Directories from Path
 **/
class RetrieveImageDirectoriesUseCase(
    private val logger: Logger,
) : KoinComponent {
    suspend operator fun invoke(
        directoryDetails: NetworkDirectoryDetails,
        recursively: Boolean = true,
    ): List<ImageDirectory> {
        logger.i { "Retrieving images from directory ${directoryDetails.fullPath}" }
        val retrieveContentsUseCase: RetrieveDirectoryContentsUseCase by inject()
        val contents = retrieveContentsUseCase(directoryDetails.fullPath)

        logger.i { "Retrieved Contents: $contents" }
        val folders = contents.folders.filter {
            logger.i { "Filtering (${it.name})" }
            it.isValid
        }
        val images = contents.images.toMutableList()

        if (recursively) {
            logger.i { "Recursively getting Images from sub-folder" }
            val recursiveUseCase: RetrieveImageDirectoriesUseCase by inject()
            folders.forEach {
                images.addAll(recursiveUseCase(it.details))
            }
        }
        return images
    }
}
