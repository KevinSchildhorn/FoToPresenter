package com.kevinschildhorn.fotopresenter.domain

import co.touchlab.kermit.Logger
import com.kevinschildhorn.fotopresenter.UseCaseFactory
import com.kevinschildhorn.fotopresenter.data.ImageDirectory
import com.kevinschildhorn.fotopresenter.data.Path
import com.kevinschildhorn.fotopresenter.data.network.NetworkDirectoryDetails
import kotlinx.datetime.LocalDate
import org.koin.core.component.KoinComponent

/**
Retrieving Image Directories from Path
 **/
class RetrieveImageDirectoriesUseCase(
    private val logger: Logger,
) : KoinComponent {
    suspend operator fun invoke(
        path: Path,
        recursively: Boolean = true,
        tags: List<String> = emptyList(),
        inclusiveTags: Boolean = false,
        startDate: LocalDate? = null,
        endDate: LocalDate? = null,
    ): List<ImageDirectory> {
        logger.i { "Retrieving images from directory $path" }
        val retrieveContentsUseCase = UseCaseFactory.retrieveDirectoryContentsUseCase
        val contents = retrieveContentsUseCase(
            path,
            recursively = recursively,
            tags = tags,
            inclusiveTags = inclusiveTags,
            startDate = startDate,
            endDate = endDate,
        )
        return contents.images
    }
}
