package com.kevinschildhorn.fotopresenter.domain.image

import co.touchlab.kermit.Logger
import com.kevinschildhorn.fotopresenter.data.ImageDirectory
import com.kevinschildhorn.fotopresenter.data.ImageSlideshowDetails
import com.kevinschildhorn.fotopresenter.data.PlaylistDetails
import com.kevinschildhorn.fotopresenter.data.network.DefaultNetworkDirectoryDetails
import com.kevinschildhorn.fotopresenter.extension.isImagePath
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/**
Retrieving Slideshow Details From Playlist Details
 **/
class RetrieveSlideshowFromPlaylistUseCase(
    private val logger: Logger,
    private val retrieveDirectoryUseCase: RetrieveImageDirectoriesUseCase,
) : KoinComponent {
    suspend operator fun invoke(
        playlistDetails: PlaylistDetails,
    ): ImageSlideshowDetails {
        logger.i { "Starting to get details from playlist ${playlistDetails.name}" }
        val directories: List<ImageDirectory> = playlistDetails.items.map { item ->
            val directoryDetails = DefaultNetworkDirectoryDetails(
                id = item.directoryId.toInt(),
                fullPath = item.directoryPath
            )
            if (item.directoryPath.isImagePath) {
                listOf(
                    ImageDirectory(
                        directoryDetails,
                        null
                    )
                )
            } else {
                retrieveDirectoryUseCase(
                    directoryDetails = directoryDetails,
                    recursively = true,
                )
            }
        }.flatten()

        return ImageSlideshowDetails(directories)
    }
}