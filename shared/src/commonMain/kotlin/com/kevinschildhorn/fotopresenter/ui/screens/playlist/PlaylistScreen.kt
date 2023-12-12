package com.kevinschildhorn.fotopresenter.ui.screens.playlist


import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun PlaylistScreen(
    viewModel: PlaylistViewModel,
    onLoginSuccess: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()
    LazyColumn {
        items(uiState.playlists) {
            Text(it.name)
        }
    }
}