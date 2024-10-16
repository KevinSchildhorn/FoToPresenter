package com.kevinschildhorn.fotopresenter.ui.screens.playlist

import co.touchlab.kermit.Logger
import com.kevinschildhorn.fotopresenter.data.Directory
import com.kevinschildhorn.fotopresenter.data.PlaylistDetails
import com.kevinschildhorn.fotopresenter.data.repositories.PlaylistRepository
import com.kevinschildhorn.fotopresenter.ui.shared.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

open class PlaylistViewModel(
    private val playlistRepository: PlaylistRepository,
    private val logger: Logger,
) : ViewModel(), KoinComponent {
    @Suppress("ktlint:standard:property-naming")
    private val _uiState = MutableStateFlow(PlaylistScreenState())
    val playlistState: StateFlow<PlaylistScreenState> = _uiState.asStateFlow()

    init {
        refreshPlaylists()
    }

    fun refreshPlaylists() {
        viewModelScope.launch(Dispatchers.Default) {
            val allPlaylists = playlistRepository.getAllPlaylists()
            _uiState.update { it.copy(playlists = allPlaylists) }
        }
    }

    fun setSelectedPlaylist(id: Long) {
        _uiState.update { it.copy(selectedId = id) }
    }

    fun getPlaylist(id: Long): PlaylistDetails? = _uiState.value.playlists.find { it.id == id }

    fun clearSelectedPlaylist() {
        _uiState.update { it.copy(selectedId = null) }
    }

    fun createPlaylist(name: String) {
        viewModelScope.launch(Dispatchers.Default) {
            playlistRepository.createPlaylist(name)
            refreshPlaylists()
        }
    }

    fun addToPlaylist(
        directory: Directory,
        playlist: PlaylistDetails,
    ) {
        logger.i { "Inserting Playlist Image ${playlist.id} as ${directory.name}" }
        viewModelScope.launch(Dispatchers.Default) {
            playlistRepository.insertPlaylistImage(
                playlistId = playlist.id,
                directory = directory,
            )?.let {
                logger.i { "Successfully inserted playlist image" }
            } ?: run {
                logger.w { "Failed to insert playlist image" }
            }
        }
    }

    fun deletePlaylist() {
        viewModelScope.launch(Dispatchers.Default) {
            _uiState.value.selectedId?.let {
                playlistRepository.deletePlaylist(it)
            }
            refreshPlaylists()
            clearSelectedPlaylist()
        }
    }
}
