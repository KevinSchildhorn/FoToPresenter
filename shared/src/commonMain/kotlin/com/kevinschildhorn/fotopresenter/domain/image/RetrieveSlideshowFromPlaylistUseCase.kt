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
) : KoinComponent {
    suspend operator fun invoke(
        playlistDetails: PlaylistDetails,
    ): ImageSlideshowDetails {
        logger.i { "Starting to get details from playlist ${playlistDetails.name}" }
        val retrieveDirectoryUseCase: RetrieveImageDirectoriesUseCase by inject()

        val directories: List<ImageDirectory> = playlistDetails.items.map { item ->
            val directoryDetails = DefaultNetworkDirectoryDetails(
                id = item.directory_id.toInt(),
                fullPath = item.directory_path
            )
            if (item.directory_path.isImagePath) {
                listOf(ImageDirectory(directoryDetails))
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