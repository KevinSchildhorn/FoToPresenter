package com.kevinschildhorn.fotopresenter.data.datasources

import co.touchlab.kermit.Logger
import com.kevinschildhorn.fotopresenter.data.PlaylistDetails
import com.kevinschildhorn.fotopresenter.data.network.NetworkHandler
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.koin.core.component.KoinComponent

class PlaylistFileDataSource(
    private val logger: Logger?,
    private val networkHandler: NetworkHandler,
) : KoinComponent {

    suspend fun importPlaylists(): List<PlaylistDetails> =
        networkHandler.getPlaylists().mapNotNull {
            try {
                Json.decodeFromString<PlaylistDetails>(it)
            } catch (e: Exception) {
                logger?.e(e) { "Error importing Playlist" }
                null
            }
        }

    suspend fun deletePlaylist(playlist: PlaylistDetails): Boolean =
        try {
            networkHandler.deletePlaylist(playlist.name)
            true
        } catch (e: Exception) {
            logger?.e(e) { "Error Deleting Playlist" }
            false
        }

    suspend fun exportPlaylist(playlist: PlaylistDetails): Boolean {
        try {
            val jsonString = Json.encodeToString(playlist)
            networkHandler.savePlaylist(playlist.name, jsonString)
        } catch (e: Exception) {
            logger?.e(e) { "Error Exporting Playlists" }
            return false
        }
        return true
    }
}