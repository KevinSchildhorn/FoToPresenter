package com.kevinschildhorn.fotopresenter.ui.screens.playlist

import com.kevinschildhorn.fotopresenter.Playlist
import com.kevinschildhorn.fotopresenter.data.PlaylistDetails
import com.kevinschildhorn.fotopresenter.ui.UiState
import com.kevinschildhorn.fotopresenter.ui.screens.common.ScreenState

data class PlaylistScreenState(
    val playlists: List<PlaylistDetails> = emptyList(),
    val selectedId: Long? = null,
    override val state: UiState = UiState.IDLE,
) : ScreenState