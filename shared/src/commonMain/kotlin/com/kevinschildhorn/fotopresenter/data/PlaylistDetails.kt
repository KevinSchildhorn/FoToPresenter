package com.kevinschildhorn.fotopresenter.data

import com.kevinschildhorn.fotopresenter.PlaylistImage
import com.kevinschildhorn.fotopresenter.data.network.DefaultNetworkDirectoryDetails

data class PlaylistDetails(
    val id: Long,
    val name: String,
    val images: List<PlaylistImage> = emptyList(),
){
    val asImageSlideshowDetails:ImageSlideshowDetails
        get() = ImageSlideshowDetails(
            directories = images.map {
                ImageDirectory(
                    DefaultNetworkDirectoryDetails(
                        id = it.directory_id.toInt(),
                        fullPath = it.directory_path
                    )
                )
            }
        )

    override fun toString(): String {
        return """
            Playlist Details:
            id: $id
            name: $name
            images: ${images.count()}
        """
    }
}