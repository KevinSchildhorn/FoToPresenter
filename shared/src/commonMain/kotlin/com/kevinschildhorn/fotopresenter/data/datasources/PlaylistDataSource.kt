package com.kevinschildhorn.fotopresenter.data.datasources

import app.cash.sqldelight.db.SqlDriver
import com.kevinschildhorn.fotopresenter.Playlist
import com.kevinschildhorn.fotopresenter.PlaylistDatabase
import com.kevinschildhorn.fotopresenter.PlaylistImage
import com.kevinschildhorn.fotopresenter.data.ImageDirectory
import org.koin.core.component.KoinComponent

class PlaylistDataSource(
    driver: SqlDriver,
) : KoinComponent {

    private val database = PlaylistDatabase(driver)

    fun createPlaylist(name: String, directories: List<ImageDirectory> = emptyList()): Playlist? {
        return try {
            //logger?.i { "Creating Playlist $name with images: ${directories.count()}" }
            database.playlistQueries.insertPlaylist(name)
            val playlist = database.playlistQueries.selectPlaylistByName(name).executeAsOne()
            //logger?.i { "Playlist Created, now adding images" }

            directories.forEach {
                insertPlaylistImage(playlistId = playlist.id, directory = it)
            }
            playlist
        } catch (e: Exception) {
            null
        }
    }

    fun getAllPlaylists(): List<Playlist> {
        return try {
            database.playlistQueries.selectAllPlaylists().executeAsList()
        } catch (e: Exception) {
            emptyList()
        }

    }

    fun getPlaylistByName(name: String): Playlist? {
        return try {
            //logger?.i { "Selecting playlist by name $name" }
            val playList: Playlist =
                database.playlistQueries.selectPlaylistByName(name).executeAsOne()
            //logger?.i { "Retrieved Playlist!" }
            playList
        } catch (e: Exception) {
            null
        }
    }

    fun insertPlaylistImage(playlistId: Long, directory: ImageDirectory): PlaylistImage? {
        //logger?.i { "Inserting Playlist Image ${directory.name}" }

        database.imageDirectoryQueries.insertPlaylistImage(
            playlist_id = playlistId,
            directory_path = directory.details.fullPath,
            directory_id = directory.id.toLong(),
        )
        return getPlaylistImage(playlistId, directory.details.fullPath)
    }

    fun getPlaylistImage(playlistId: Long, directoryPath: String): PlaylistImage? {
        return try {
            //logger?.i { "Selecting Playlist Image $playlistId" }
            val image: PlaylistImage =
                database.imageDirectoryQueries.selectPlaylistImage(playlistId, directoryPath)
                    .executeAsOne()
            //logger?.i { "Selecting Playlist Image" }
            image
        } catch (e: Exception) {
            null
        }
    }

    fun deletePlaylist(id: Long): Boolean {
        return try {
            val playlist = database.playlistQueries.selectPlaylistById(id).executeAsOne()
            database.playlistQueries.deletePlaylist(playlist.name)
            database.imageDirectoryQueries.deletePlaylist(playlist.id)
            true
        } catch (e: Exception) {
            false
        }
    }
}