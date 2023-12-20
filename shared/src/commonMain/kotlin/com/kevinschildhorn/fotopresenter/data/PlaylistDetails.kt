package com.kevinschildhorn.fotopresenter.data

import com.kevinschildhorn.fotopresenter.PlaylistItems
import com.kevinschildhorn.fotopresenter.data.network.DefaultNetworkDirectoryDetails
import com.kevinschildhorn.fotopresenter.extension.isImagePath

data class PlaylistDetails(
    val id: Long,
    val name: String,
    val items: List<PlaylistItems> = emptyList(),
){
    override fun toString(): String {
        return """
            Playlist Details:
            id: $id
            name: $name
            images: ${items.count()}
        """
    }
}