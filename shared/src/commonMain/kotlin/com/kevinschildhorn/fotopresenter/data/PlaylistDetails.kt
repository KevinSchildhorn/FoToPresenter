package com.kevinschildhorn.fotopresenter.data

import com.kevinschildhorn.fotopresenter.PlaylistItems
import kotlinx.serialization.Serializable

@Serializable
data class PlaylistDetails(
    val id: Long,
    val name: String,
    val items: List<PlaylistItem> = emptyList(),
) {
    override fun toString(): String {
        return """
            Playlist Details:
            id: $id
            name: $name
            images: ${items.count()}
        """
    }
}

@Serializable
data class PlaylistItem(
    val id: Long,
    val playlistId: Long,
    val directoryPath: Path,
    val directoryId: Long,
) {
    constructor(item: PlaylistItems) : this(
        id = item.id,
        playlistId = item.playlist_id,
        directoryPath = Path(item.directory_path),
        directoryId = item.directory_id,
    )
}
