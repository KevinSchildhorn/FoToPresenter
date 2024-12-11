package com.kevinschildhorn.fotopresenter.data.datasources

import app.cash.sqldelight.db.SqlDriver
import co.touchlab.kermit.Logger
import com.kevinschildhorn.fotopresenter.Playlist
import com.kevinschildhorn.fotopresenter.PlaylistDatabase
import com.kevinschildhorn.fotopresenter.PlaylistItems
import com.kevinschildhorn.fotopresenter.data.Directory
import com.kevinschildhorn.fotopresenter.data.ImageDirectory
import com.kevinschildhorn.fotopresenter.data.Path
import com.kevinschildhorn.fotopresenter.data.PlaylistDetails
import com.kevinschildhorn.fotopresenter.data.PlaylistItem
import org.koin.core.component.KoinComponent

class PlaylistSQLDataSource(
    driver: SqlDriver,
    private val logger: Logger? = null,
) : KoinComponent {
    private val database = PlaylistDatabase(driver)

    fun createPlaylist(
        name: String,
        directories: List<ImageDirectory> = emptyList(),
    ): Playlist? {
        return try {
            logger?.i { "Creating Playlist $name with images: ${directories.count()}" }
            database.playlistQueries.insertPlaylist(name)
            val playlist = database.playlistQueries.selectPlaylistByName(name).executeAsOne()
            logger?.i { "Playlist Created, now adding images" }

            directories.forEach {
                insertPlaylistImage(playlistId = playlist.id, directory = it)
            }
            playlist
        } catch (e: Exception) {
            null
        }
    }

    fun getAllPlaylists(): List<PlaylistDetails> {
        return try {
            database.playlistQueries.selectAllPlaylists().executeAsList().map { playlist ->
                val images =
                    database.playlistItemsQueries.selectPlaylistImages(playlist.id).executeAsList()
                PlaylistDetails(playlist.id, playlist.name, images.map { PlaylistItem(it) })
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    fun getPlaylistByName(name: String): PlaylistDetails? {
        return try {
            logger?.i { "Selecting playlist by name $name" }
            val playList: Playlist =
                database.playlistQueries.selectPlaylistByName(name).executeAsOne()
            val images =
                database.playlistItemsQueries.selectPlaylistImages(playList.id).executeAsList()
            logger?.i { "Retrieved Playlist!" }
            PlaylistDetails(playList.id, playList.name, images.map { PlaylistItem(it) })
        } catch (e: Exception) {
            null
        }
    }

    fun getPlaylistById(id: Long): PlaylistDetails? {
        return try {
            logger?.i { "Selecting playlist by id $id" }
            val playList: Playlist =
                database.playlistQueries.selectPlaylistById(id).executeAsOne()
            val images =
                database.playlistItemsQueries.selectPlaylistImages(playList.id).executeAsList()
            logger?.i { "Retrieved Playlist!" }
            PlaylistDetails(playList.id, playList.name, images.map { PlaylistItem(it) })
        } catch (e: Exception) {
            null
        }
    }

    fun insertPlaylistImage(
        playlistId: Long,
        directory: Directory,
    ): PlaylistItem? {
        logger?.i { "Inserting Playlist Image ${directory.name}" }
        database.playlistItemsQueries.insertPlaylistImage(
            playlist_id = playlistId,
            directory_path = directory.details.fullPath.toString(),
            directory_id = directory.id.toLong(),
        )
        return getPlaylistImage(playlistId, directory.details.fullPath)
    }

    fun getPlaylistImage(
        playlistId: Long,
        directoryPath: Path,
    ): PlaylistItem? {
        return try {
            logger?.i { "Selecting Playlist Image $playlistId" }
            val image: PlaylistItems =
                database.playlistItemsQueries.selectPlaylistImage(playlistId, directoryPath.toString())
                    .executeAsOne()
            logger?.i { "Selecting Playlist Image" }
            PlaylistItem(image)
        } catch (e: Exception) {
            null
        }
    }

    fun deletePlaylist(id: Long): Boolean {
        return try {
            val playlist = database.playlistQueries.selectPlaylistById(id).executeAsOne()
            database.playlistQueries.deletePlaylist(playlist.name)
            database.playlistItemsQueries.deletePlaylist(playlist.id)
            true
        } catch (e: Exception) {
            false
        }
    }
}
