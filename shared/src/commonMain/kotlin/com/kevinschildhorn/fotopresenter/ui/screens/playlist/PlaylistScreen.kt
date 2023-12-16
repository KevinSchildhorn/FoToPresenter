package com.kevinschildhorn.fotopresenter.ui.screens.playlist


import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.kevinschildhorn.fotopresenter.ui.screens.playlist.composables.PlaylistOverlay

@Composable
fun PlaylistScreen(
    viewModel: PlaylistViewModel,
    onLoginSuccess: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.refreshPlaylists()
    }

    PlaylistOverlay(
        uiState.playlists,
        onClick = {

        }, onDelete = {

        }, onEdit = {

        }, onCreate = {

        })
}