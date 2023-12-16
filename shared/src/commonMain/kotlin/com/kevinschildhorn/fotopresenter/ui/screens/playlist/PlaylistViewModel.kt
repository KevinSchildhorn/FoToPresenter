package com.kevinschildhorn.fotopresenter.ui.screens.playlist

import co.touchlab.kermit.Logger
import com.kevinschildhorn.fotopresenter.data.repositories.PlaylistRepository
import com.kevinschildhorn.fotopresenter.ui.shared.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.koin.core.component.KoinComponent

class PlaylistViewModel(
    private val playlistRepository: PlaylistRepository,
    private val logger: Logger,
) : ViewModel(), KoinComponent {

    private val _uiState = MutableStateFlow(PlaylistScreenState())
    val uiState: StateFlow<PlaylistScreenState> = _uiState.asStateFlow()

    init {
        refreshPlaylists()
    }

    fun refreshPlaylists(){
        val allPlaylists = playlistRepository.getAllPlaylists()
        _uiState.update { it.copy(playlists = allPlaylists) }
    }

    fun setSelectedPlaylist(id:Long){
        _uiState.update { it.copy(selectedId = id) }
    }

    fun clearSelectedPlaylist(){
        _uiState.update { it.copy(selectedId = null) }
    }

    fun createPlaylist(name: String){
        playlistRepository.createPlaylist(name)
        refreshPlaylists()
    }

    fun deletePlaylist(){
        _uiState.value.selectedId?.let {
            playlistRepository.deletePlaylist(it)
        }
        refreshPlaylists()
        clearSelectedPlaylist()
    }
}