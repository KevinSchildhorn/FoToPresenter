package com.kevinschildhorn.fotopresenter.data.repositories

import com.kevinschildhorn.fotopresenter.Playlist
import com.kevinschildhorn.fotopresenter.PlaylistItems
import com.kevinschildhorn.fotopresenter.data.Directory
import com.kevinschildhorn.fotopresenter.data.ImageDirectory
import com.kevinschildhorn.fotopresenter.data.PlaylistDetails
import com.kevinschildhorn.fotopresenter.data.datasources.PlaylistDataSource

class PlaylistRepository(
    private val playlistDataSource: PlaylistDataSource,
) {

    fun createPlaylist(name: String, directories: List<ImageDirectory> = emptyList()): Playlist? =
        playlistDataSource.createPlaylist(name, directories)

    fun getAllPlaylists(): List<PlaylistDetails> =
        playlistDataSource.getAllPlaylists()

    fun getPlaylistByName(name: String): PlaylistDetails? =
        playlistDataSource.getPlaylistByName(name)

    fun insertPlaylistImage(playlistId: Long, directory: Directory): PlaylistItems? =
        playlistDataSource.insertPlaylistImage(playlistId, directory)

    fun getPlaylistImage(playlistId: Long, directoryPath: String): PlaylistItems? =
        playlistDataSource.getPlaylistImage(playlistId, directoryPath)

    fun deletePlaylist(id: Long): Boolean =
        playlistDataSource.deletePlaylist(id)
}