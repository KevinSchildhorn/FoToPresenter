package com.kevinschildhorn.fotopresenter.domain

import co.touchlab.kermit.Logger
import com.kevinschildhorn.fotopresenter.UseCaseFactory
import com.kevinschildhorn.fotopresenter.data.ImageDirectory
import com.kevinschildhorn.fotopresenter.data.network.NetworkDirectoryDetails
import org.koin.core.component.KoinComponent

/**
Retrieving Image Directories from Path
 **/
class RetrieveImageDirectoriesUseCase(
    private val logger: Logger,
) : KoinComponent {
    suspend operator fun invoke(
        directoryDetails: NetworkDirectoryDetails,
        recursively: Boolean = true,
        tags: List<String> = emptyList(),
        inclusiveTags: Boolean = false,
    ): List<ImageDirectory> {
        logger.i { "Retrieving images from directory ${directoryDetails.fullPath}" }
        val retrieveContentsUseCase = UseCaseFactory.retrieveDirectoryContentsUseCase
        val contents = retrieveContentsUseCase(
            directoryDetails.fullPath,
            recursively = recursively,
            tags,
            inclusiveTags
        )
        return contents.images
    }
}
