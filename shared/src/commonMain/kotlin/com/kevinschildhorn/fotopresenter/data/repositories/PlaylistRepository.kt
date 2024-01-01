package com.kevinschildhorn.fotopresenter.data.repositories

import com.kevinschildhorn.fotopresenter.Playlist
import com.kevinschildhorn.fotopresenter.data.Directory
import com.kevinschildhorn.fotopresenter.data.ImageDirectory
import com.kevinschildhorn.fotopresenter.data.PlaylistDetails
import com.kevinschildhorn.fotopresenter.data.PlaylistItem
import com.kevinschildhorn.fotopresenter.data.datasources.PlaylistFileDataSource
import com.kevinschildhorn.fotopresenter.data.datasources.PlaylistSQLDataSource

class PlaylistRepository(
    private val playlistSQLDataSource: PlaylistSQLDataSource,
    private val playlistFileDataSource: PlaylistFileDataSource,
) {

    suspend fun createPlaylist(name: String, directories: List<ImageDirectory> = emptyList()): Playlist? {
        val playlist = playlistSQLDataSource.createPlaylist(name, directories)
        playlistSQLDataSource.getPlaylistByName(name)?.let {
            playlistFileDataSource.exportPlaylist(it)
        }
        return playlist
    }

    suspend fun getAllPlaylists(): List<PlaylistDetails> {
        playlistFileDataSource.importPlaylists()
        return playlistSQLDataSource.getAllPlaylists()
    }

    suspend fun insertPlaylistImage(playlistId: Long, directory: Directory): PlaylistItem? {
        val item = playlistSQLDataSource.insertPlaylistImage(playlistId, directory)
        playlistSQLDataSource.getPlaylistById(playlistId)?.let {
            playlistFileDataSource.exportPlaylist(it)
        }
        return item
    }

    suspend fun deletePlaylist(id: Long): Boolean {
        playlistSQLDataSource.getPlaylistById(id)?.let {
            playlistFileDataSource.deletePlaylist(it)
        }
        return playlistSQLDataSource.deletePlaylist(id)
    }

}